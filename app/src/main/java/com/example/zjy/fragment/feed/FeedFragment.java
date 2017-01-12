package com.example.zjy.fragment.feed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.zjy.adapter.feed.FeedFragmentMultiAdapter;
import com.example.zjy.bean.FeedBean;
import com.example.zjy.eye.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.HeaderAndFooterWrapper;
import com.example.zjy.niklauslibrary.rvhelper.wrapper.LoadMoreWrapper;
import com.example.zjy.niklauslibrary.util.FontHelper;
import com.example.zjy.niklauslibrary.util.RetrofitUtil;
import com.example.zjy.niklauslibrary.util.TimeUtils;
import com.example.zjy.utils.Constants;
import com.example.zjy.utils.ParseUtils;

import butterknife.Bind;

/**
 * Created by zjy on 2017/1/5.
 * feed的Fragment
 */

public class FeedFragment extends BaseFragment implements RetrofitUtil.DownListener {
    @Bind(R.id.rv_feed)
    RecyclerView rv_feed;
    private FeedFragmentMultiAdapter feedFragmentMultiAdapter;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private TextView tv_publish_date;
    private LoadMoreWrapper loadMoreWrapper;
    private int flag ;
    private View head_view;

    @Override
    public int getContentId() {
        return R.layout.fragment_feed;
    }

    @Override
    protected void init(View view) {
        rv_feed.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //添加分割线
        feedFragmentMultiAdapter = new FeedFragmentMultiAdapter(getContext());
        //添加头部
        headerAndFooterWrapper = new HeaderAndFooterWrapper(feedFragmentMultiAdapter);
        head_view = LayoutInflater.from(getContext()).inflate(R.layout.feed_head_view,null);
        tv_publish_date = (TextView) head_view.findViewById(R.id.tv_publish_date);
        headerAndFooterWrapper.addHeaderView(head_view);
        feedFragmentMultiAdapter.setFragmentManager(getChildFragmentManager());
        View empty_view = LayoutInflater.from(getContext()).inflate(R.layout.feed_empty_view,null);
        loadMoreWrapper = new LoadMoreWrapper(headerAndFooterWrapper);
        loadMoreWrapper.setLoadMoreView(empty_view);
        rv_feed.setAdapter(loadMoreWrapper);

        loadDatas();
    }

    @Override
    protected void loadDatas() {
        new RetrofitUtil(getContext()).setDownListener(this).downJson(Constants.URL_HANDPICK,0x001);
    }

    @Override
    protected void bindListener() {
        rv_feed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //让head_view悬停在顶部
                    head_view.setY(0);
                Log.i("tag", "onScrolled: "+dy);
            }
        });
    }

    @Override
    public Object paresJson(String json, int requestCode) {
        Log.i("tag", "paresJson: "+json);
        if(json != null) {
            return ParseUtils.parseFeed(json);
        }
        return null;
    }

    @Override
    public void downSucc(Object object, int requestCode) {
        if(object != null){
            final FeedBean feedBean = (FeedBean) object;
            long date = feedBean.getDate();
            //设置头部日期
            tv_publish_date.setText("- " + TimeUtils.CSTFormat(date) + " -");
            FontHelper.injectFont2(tv_publish_date);
            feedFragmentMultiAdapter.addDataAll(feedBean.getSectionList());
            //添加完数据通知刷新
            loadMoreWrapper.notifyDataSetChanged();
            loadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    String nextPageUrl = feedBean.getNextPageUrl();
                    Log.i("tag", "onLoadMoreRequested: "+nextPageUrl);
                    new RetrofitUtil(getContext()).setDownListener(FeedFragment.this).downJson(nextPageUrl,0x001);
                }
            });

        }
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
