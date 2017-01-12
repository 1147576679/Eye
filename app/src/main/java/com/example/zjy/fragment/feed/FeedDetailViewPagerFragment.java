package com.example.zjy.fragment.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjy.bean.FeedBean.SectionListBean.ItemListBean;
import com.example.zjy.eye.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.imageloader.ImageLoader;
import com.example.zjy.niklauslibrary.imageloader.ImageLoaderUtil;
import com.example.zjy.niklauslibrary.util.TimeUtils;

import butterknife.Bind;

/**
 * Created by zjy on 2017/1/11.
 * FeedFragment RecyclerView 多布局之FeedSection的item跳转页面
 */

public class FeedDetailViewPagerFragment extends BaseFragment {

    @Bind(R.id.iv_play)
    ImageView ivPlay;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_category_video_duration)
    TextView tvCategoryVideoDuration;
    @Bind(R.id.tv_favorites)
    TextView tvFavorites;
    @Bind(R.id.tv_shares)
    TextView tvShares;
    @Bind(R.id.tv_reply)
    TextView tvReply;
    //加载图片的工具类
    private ImageLoaderUtil imageLoaderUtil;

    @Override
    public int getContentId() {
        return R.layout.fragment_feed_detail_vp;
    }

    public static FeedDetailViewPagerFragment newInstance(ItemListBean itemListBean) {
        Bundle args = new Bundle();
        args.putSerializable("data", itemListBean);
        FeedDetailViewPagerFragment fragment = new FeedDetailViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init(View view) {
        imageLoaderUtil = new ImageLoaderUtil();
    }

    @Override
    protected void getDatas(Bundle bundle) {
        ItemListBean itemListBean = (ItemListBean) bundle.getSerializable("data");
        if(itemListBean.getType().equals("video")) {
            loadImage(ivPlay, itemListBean.getData().getCover().getFeed());
            Log.i("tag", "getDatas: " + itemListBean.getData().getCover().getFeed());
            loadImage(ivBg, itemListBean.getData().getCover().getBlurred());
            tvTitle.setText(itemListBean.getData().getTitle());
            tvCategoryVideoDuration.setText("#" + itemListBean.getData().getCategory()
                    + "  " + "/" + "  " +
                    TimeUtils.secToTime(itemListBean.getData().getDuration()));
            tvContent.setText(itemListBean.getData().getDescription());
        tvFavorites.setText(itemListBean.getData().getConsumption().getCollectionCount()+"");
        tvShares.setText(itemListBean.getData().getConsumption().getShareCount()+"");
        tvReply.setText(itemListBean.getData().getConsumption().getReplyCount()+"");
        }
    }

    public void loadImage(ImageView iv,String url){
        ImageLoader loader = new ImageLoader.Builder()
                .placeHolder(R.drawable.recommend_bg_liked)
                .strategy(ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI)
                .url(url)
                .imgView(iv)
                .build();
        imageLoaderUtil.loadImage(getContext(),loader);
    }
}
