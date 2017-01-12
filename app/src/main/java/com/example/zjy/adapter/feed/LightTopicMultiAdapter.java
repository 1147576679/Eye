package com.example.zjy.adapter.feed;

import android.content.Context;

import com.example.zjy.bean.FeedBean;
import com.example.zjy.eye.R;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ItemViewDelegate;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.util.TimeUtils;

/**
 * Created by zjy on 2017/1/6.
 * FeedMultiAdapater中第二种布局（LightTopicSection的多布局适配器）
 */

public class LightTopicMultiAdapter extends MultiItemTypeAdapter<FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean> {
    private static final String VIDEO = "video";
    private static final String ACTIONCARD = "actionCard";
    public LightTopicMultiAdapter(Context context) {
        super(context);
        addItemViewDelegate(new VideoDeletage());
        addItemViewDelegate(new ActionCard());
    }

    public class VideoDeletage implements ItemViewDelegate<FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_rv_light_topic_rv_item_video;
        }

        @Override
        public boolean isForViewType(FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean item, int position) {
            return VIDEO.equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean innerItemListBean, int position) {
            String time = TimeUtils.secToTime(innerItemListBean.getData().getDuration());
            holder.setImageUrl(R.id.iv_horizontal, innerItemListBean.getData().getCover().getFeed())
                    .setText(R.id.tv_title, innerItemListBean.getData().getTitle())
                    .setText(R.id.tv_category_video_duration, "#" + innerItemListBean.getData().getCategory() + "  " + "/" + "  " + time);
        }
    }

    public class ActionCard implements ItemViewDelegate<FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_rv_light_topic_rv_item_action_card;
        }

        @Override
        public boolean isForViewType(FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean item, int position) {
            return ACTIONCARD.equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean innerItemListBean, int position) {
            holder.setText(R.id.tv_action_card,innerItemListBean.getData().getText());
        }
    }
}
