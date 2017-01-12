package com.example.zjy.fragment.feed;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjy.eye.R;
import com.example.zjy.niklauslibrary.base.BaseFragment;
import com.example.zjy.niklauslibrary.util.ImageViewFit;
import com.example.zjy.niklauslibrary.util.TimeUtils;

import butterknife.Bind;

/**
 * Created by zjy on 2017/1/6.
 */

public class CategoryFragment extends BaseFragment {
    @Bind(R.id.iv_vp_pic)
    ImageView iv_vp_pic;
    private String url;

    @Bind(R.id.tv_data_title)
    TextView tv_data_title;

    @Bind(R.id.tv_category_video_duration)
    TextView tv_category_video_duration;

    @Override
    public int getContentId() {
        return R.layout.fragment_category;
    }

    public static CategoryFragment newInstance(String url,String title,String category,int duration) {
        Bundle args = new Bundle();
        args.putString("url",url);
        args.putString("title",title);
        args.putString("category",category);
        args.putInt("duration",duration);
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void getDatas(Bundle bundle) {
        url = bundle.getString("url");
        String title = bundle.getString("title");
        String category = bundle.getString("category");
        int duration = bundle.getInt("duration");
        String time = TimeUtils.secToTime(duration);
        tv_data_title.setText(title);
        tv_category_video_duration.setText("#" + category + "  " + "/" + "  " + time);
        ImageViewFit.dynamicImage(url,iv_vp_pic,getContext());
    }

}
