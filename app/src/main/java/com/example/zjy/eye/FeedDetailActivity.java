package com.example.zjy.eye;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.zjy.bean.FeedBean.SectionListBean.ItemListBean;
import com.example.zjy.fragment.feed.FeedDetailViewPagerFragment;
import com.example.zjy.niklauslibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class FeedDetailActivity extends BaseActivity {
    @Bind(R.id.vp_feed_detail)
    ViewPager vp_feed_detail;
    private FeedViewPagerAdapter vpAdapter;
    @Override
    public int getContentId() {
        return R.layout.activity_feed_detail;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        ArrayList<ItemListBean> data =  (ArrayList<ItemListBean>) intent.getSerializableExtra("data");
        int position = intent.getIntExtra("position", -1);
        Log.i("tag", "init: "+position);
        vpAdapter = new FeedViewPagerAdapter(getSupportFragmentManager(),data);
        vp_feed_detail.setAdapter(vpAdapter);
        vp_feed_detail.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {

            }
        });
    }


    private static class FeedViewPagerAdapter extends FragmentPagerAdapter{
        private List<ItemListBean> datas;
        public FeedViewPagerAdapter(FragmentManager fm, List<ItemListBean> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return FeedDetailViewPagerFragment.newInstance(datas.get(position));
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
