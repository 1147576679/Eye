package com.example.zjy.adapter.feed;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.zjy.bean.FeedBean;
import com.example.zjy.eye.FeedDetailActivity;
import com.example.zjy.eye.R;
import com.example.zjy.niklauslibrary.rvhelper.adapter.MultiItemTypeAdapter;
import com.example.zjy.niklauslibrary.rvhelper.base.ItemViewDelegate;
import com.example.zjy.niklauslibrary.rvhelper.base.ViewHolder;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.HeaderAndFooterWrapper;
import com.example.zjy.niklauslibrary.widget.loopviewpager.LoopViewPager;
import com.example.zjy.niklauslibrary.widget.vpindicator.TabView;
import com.example.zjy.widget.FeedFooterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjy on 2017/1/5.
 */

public class FeedFragmentMultiAdapter extends MultiItemTypeAdapter<FeedBean.SectionListBean> {
    private static final String FEEDSECTION = "feedSection";
    private static final String LIGHTTOPICSECTION = "lightTopicSection";
    private static final String CATEGORYSECTION = "categorySection";
    private static final String RANKLISTSECTION = "rankListSection";
    private Context context;
    private FragmentManager fragmentManager;
    public int id = 1000;

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public FeedFragmentMultiAdapter(Context context) {
        super(context);
        this.context = context;
        addItemViewDelegate(new FeedSectionDelegate());
        addItemViewDelegate(new LightTopicSectionDelegate());
        addItemViewDelegate(new CategorySectionDelegate());
    }


    //FeedSection 布局（只显示一张图片布局）
    public class FeedSectionDelegate implements ItemViewDelegate<FeedBean.SectionListBean>{
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_rv_feed_section;
        }

        @Override
        public boolean isForViewType(FeedBean.SectionListBean section, int position) {
            return FEEDSECTION.equals(section.getType());
        }

        @Override
        public void convert(ViewHolder holder, final FeedBean.SectionListBean sectionListBean, int position) {
            RecyclerView rv_feed_section = holder.getView(R.id.rv_single_iv);
            rv_feed_section.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            FeedSectionMultiAdapter adapter = new FeedSectionMultiAdapter(context);
            adapter.setOnItemSectionListener(new FeedSectionMultiAdapter.onItemSectionListener() {
                @Override
                public void click(View view, int position) {
                    Intent intent = new Intent(context, FeedDetailActivity.class);
                    ArrayList<FeedBean.SectionListBean.ItemListBean> itemList = (ArrayList<FeedBean.SectionListBean.ItemListBean>) sectionListBean.getItemList();
                    intent.putExtra("data",itemList);
                    intent.putExtra("position",position);
                    context.startActivity(intent);
                }
            });
            //TODO 底部视图显示不全
            //根据数据来决定是否要加底部
            if(sectionListBean.getFooter() != null){
                HeaderAndFooterWrapper headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
//                View footer_view = LayoutInflater.from(context).inflate(R.layout.feed_footer_view,null);
//                TextView tv_text = (TextView) footer_view.findViewById(R.id.tv_footer_text);
//                tv_text.setText(sectionListBean.getFooter().getData().getText());
                FeedFooterView footer_view = new FeedFooterView(context);
                footer_view.loadData(sectionListBean);
                headerAndFooterWrapper.addFootView(footer_view);
                adapter.addDataAll(sectionListBean.getItemList());
                headerAndFooterWrapper.notifyDataSetChanged();
                rv_feed_section.setAdapter(headerAndFooterWrapper);
            }else{
                rv_feed_section.setAdapter(adapter);
                adapter.addDataAll(sectionListBean.getItemList());
            }

        }
    }

    //LightTopicSection 布局（显示一张图片和一个横向RecyclerView布局）
    public class LightTopicSectionDelegate implements ItemViewDelegate<FeedBean.SectionListBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_rv_light_topic;
        }

        @Override
        public boolean isForViewType(FeedBean.SectionListBean item, int position) {
            return LIGHTTOPICSECTION.equals(item.getType()) || RANKLISTSECTION.equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, final FeedBean.SectionListBean sectionListBean, int position) {
            RecyclerView rv_light_topic = holder.getView(R.id.rv_light_topic);
            rv_light_topic.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
//            sectionListBean.getItemList().get(0).getData().getH
            //多布局适配器
            LightTopicMultiAdapter lightTopicMultiAdapter = new LightTopicMultiAdapter(context);
            //判断是否有头部
            FeedBean.SectionListBean.ItemListBean.DataBean.HeaderBean header =
                    sectionListBean.getItemList().get(0).getData().getHeader();
            holder.setImageUrl(R.id.iv_light_topic_head,header.getCover());
//            if(header != null){
//                HeaderAndFooterWrapper headAdapter = new HeaderAndFooterWrapper(lightTopicMultiAdapter);
//                View head_view = LayoutInflater.from(context).inflate(R.layout.head_layout_light_topic,null);
//                ImageView iv = (ImageView) head_view.findViewById(R.id.iv_light_topic_head);
//                Glide.with(context)
//                        .load(header.getCover())
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .placeholder(R.drawable.recommend_bg_liked)
//                        .into(iv);
//                headAdapter.addHeaderView(head_view);
//                rv_light_topic.setAdapter(headAdapter);
//            }else{
            rv_light_topic.setAdapter(lightTopicMultiAdapter);
            lightTopicMultiAdapter.addDataAll(sectionListBean.getItemList().get(0).getData().getItemList());
//            CommonAdapter<FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean> commonAdapter =
//                    new CommonAdapter<FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean>(context,R.layout.item_rv_light_topic_rv_item_video) {
//                @Override
//                protected void convert(ViewHolder holder, FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean itemListBean, int position) {
//                    if ("video".equals(itemListBean.getType())) {
//                        String time = TimeUtils.secToTime(itemListBean.getData().getDuration());
//                        holder.setImageUrl(R.id.iv_horizontal, itemListBean.getData().getCover().getFeed())
//                                .setText(R.id.tv_title, itemListBean.getData().getTitle())
//                                .setText(R.id.tv_category_video_duration, "#" + itemListBean.getData().getCategory() + "  " + "/" + "  " + time);
//                    }else if("actionCard".equals(itemListBean.getType())){
//
//                    }
//                }
//            };
//            rv_light_topic.setAdapter(commonAdapter);
//            commonAdapter.addDataAll(sectionListBean.getItemList().get(0).getData().getItemList());
        }
    }

    //CategorySection 布局（ViewPager）
    public class CategorySectionDelegate implements ItemViewDelegate<FeedBean.SectionListBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_rv_category_section;
        }

        @Override
        public boolean isForViewType(FeedBean.SectionListBean item, int position) {
            return CATEGORYSECTION.equals(item.getType());
        }

        @Override
        public void convert(ViewHolder holder, FeedBean.SectionListBean sectionListBean, int position) {
            List<FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean> itemList =
                    sectionListBean.getItemList().get(0).getData().getItemList();
            holder.setText(R.id.tv_category_title, (String) sectionListBean.getItemList().get(0).getData().getHeader().getTitle())
                    .setText(R.id.tv_sub_title,sectionListBean.getItemList().get(0).getData().getHeader().getSubTitle());
            FrameLayout frameLayout = holder.getView(R.id.fl_vp_placeholder);
//            frameLayout.removeAllViews();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            if(frameLayout.getChildCount() == 0) {
                LoopViewPager vp = new LoopViewPager(context);
                vp.setId(id++);
                //给ViewPager设置Padding可以达到前后图片可以留一些，可以看到三张图片的viewpager
                int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.padding_left);
                vp.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
                vp.setClipToPadding(false);
                TabView indicator = holder.getView(R.id.vp_indicator);
                indicator.setCount(itemList.size());
                CategoryVpAdapter categoryVpAdapter = new CategoryVpAdapter(fragmentManager, itemList);
                vp.setAdapter(categoryVpAdapter);
                frameLayout.addView(vp, params);
                indicator.setViewPager(vp);
            }
        }
    }

}
