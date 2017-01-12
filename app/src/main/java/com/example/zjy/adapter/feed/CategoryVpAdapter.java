package com.example.zjy.adapter.feed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.zjy.bean.FeedBean;
import com.example.zjy.fragment.feed.CategoryFragment;
import com.example.zjy.niklauslibrary.widget.loopviewpager.LoopViewPager;

import java.util.List;

/**
 * Created by zjy on 2017/1/6.
 */

public class CategoryVpAdapter extends FragmentStatePagerAdapter {
    private List<FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean> datas;
    public CategoryVpAdapter(FragmentManager fm,List<FeedBean.SectionListBean.ItemListBean.DataBean.InnerItemListBean> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        position = LoopViewPager.toRealPosition(position,getCount());
        return CategoryFragment.newInstance(datas.get(position % getCount()).getData().getCover().getFeed(),
                datas.get(position % getCount()).getData().getTitle(),
                datas.get(position % getCount()).getData().getCategory(),
                datas.get(position % getCount()).getData().getDuration());
    }

    @Override
    public int getCount() {
        return datas.size();
    }

}
