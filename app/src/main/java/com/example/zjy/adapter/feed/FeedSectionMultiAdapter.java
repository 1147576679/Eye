package com.example.zjy.adapter.feed;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zjy.bean.FeedBean;
import com.example.zjy.eye.R;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ItemViewDelegate;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.util.FontHelper;
import com.example.zjy.widget.FeedSectionLayout;

/**
 * Created by zjy on 2017/1/9.
 * 精选多布局中type为FeedSection的item的多布局适配器
 */

public class FeedSectionMultiAdapter extends MultiItemTypeAdapter<FeedBean.SectionListBean.ItemListBean> {
    private static final String VIDEO = "video";
    private static final String TEXTHEADER = "textHeader";
    private static final String BANNER = "banner2";
    private Context context;
    private onItemSectionListener onItemSectionListener;
    public FeedSectionMultiAdapter(Context context) {
        super(context);
        this.context = context;
        addItemViewDelegate(new SingImageDelegate());
        addItemViewDelegate(new BannerDelegate());
        addItemViewDelegate(new TextHeaderDelegate());
    }
    public void setOnItemSectionListener(FeedSectionMultiAdapter.onItemSectionListener onItemSectionListener) {
        this.onItemSectionListener = onItemSectionListener;
    }

    //单图布局
    public class SingImageDelegate implements ItemViewDelegate<FeedBean.SectionListBean.ItemListBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_rv_feed_section_feed_layout;
        }

        @Override
        public boolean isForViewType(FeedBean.SectionListBean.ItemListBean item, int position) {
            return VIDEO.equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, final FeedBean.SectionListBean.ItemListBean itemListBean, final int position) {
//            TextView tv_title = holder.getView(R.id.tv_title);
//            FontHelper.injectFont(tv_title);
//            String time = TimeUtils.secToTime(itemListBean.getData().getDuration());
//            final LinearLayout ll_content = holder.getView(R.id.ll_content);
//            final ImageView iv_pic = holder.getView(R.id.iv_pic);
//            ImageLightUtils.changeLight(iv_pic,-64);
//            holder.setText(R.id.tv_title, itemListBean.getData().getTitle())
//                    .setText(R.id.tv_category_video_duration, "#" + itemListBean.getData().getCategory() + "  " + "/" + "  " + time);
//            //动态设置imageview的宽高
//            ImageViewFit.dynamicImage(itemListBean.getData().getCover().getDetail(), iv_pic,context);
//
//            if (itemListBean.getData().getAuthor() != null) {
//                holder.setText(R.id.tv_author_name, itemListBean.getData().getAuthor().getName());
//            } else {
//                TextView view = holder.getView(R.id.tv_author_name);
//                view.setVisibility(View.GONE);
//            }
            final FeedSectionLayout feedSectionLayout = holder.getView(R.id.feed_section_layout);
            feedSectionLayout.setData(itemListBean);
            holder.setOnClickListener(R.id.feed_section_layout, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.i("tag", "onClick: 点击了");
                   if(onItemSectionListener != null){
                       onItemSectionListener.click(feedSectionLayout,position);
                   }
                }
            });
        }
    }

    //广告布局
    public class BannerDelegate implements ItemViewDelegate<FeedBean.SectionListBean.ItemListBean>{
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_rv_feed_section_banner;
        }

        @Override
        public boolean isForViewType(FeedBean.SectionListBean.ItemListBean item, int position) {
            return BANNER.equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, FeedBean.SectionListBean.ItemListBean itemListBean, int position) {
            ImageView iv_banner = holder.getView(R.id.iv_banner);
            Glide.with(context.getApplicationContext())
                    .load(itemListBean.getData().getImage())
                    .placeholder(R.drawable.recommend_bg_liked)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(0.1f)
                    .crossFade(500)
                    .into(iv_banner);
//            final ImageView banner = new ImageView(context);
//            Glide.with(context.getApplicationContext())
//                    .load(itemListBean.getData().getImage())
//                    .asBitmap()
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                            int width = resource.getWidth();
//                            int height = resource.getHeight();
//                            float widthPixels = context.getResources().getDisplayMetrics().widthPixels;
//                            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                            params.width = (int) widthPixels;
//                            params.height = (int) (height * (widthPixels / width));
//                            banner.setLayoutParams(params);
//                            iv_banner.addView(banner);
//                        }
//                    });

        }
    }

    //列表的发布的时间
    public class TextHeaderDelegate implements ItemViewDelegate<FeedBean.SectionListBean.ItemListBean>{
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_rv_feed_section_text_header;
        }

        @Override
        public boolean isForViewType(FeedBean.SectionListBean.ItemListBean item, int position) {
            return TEXTHEADER.equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, FeedBean.SectionListBean.ItemListBean itemListBean, int position) {
            TextView text_header = holder.getView(R.id.text_header);
            text_header.setText(itemListBean.getData().getText());
            FontHelper.injectFont2(text_header);
        }
    }

    public interface onItemSectionListener{
        void click(View view, int position);
    }
}
