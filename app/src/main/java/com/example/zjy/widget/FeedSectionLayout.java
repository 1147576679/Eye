package com.example.zjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zjy.bean.FeedBean;
import com.example.zjy.eye.R;
import com.example.zjy.niklauslibrary.util.FontHelper;
import com.example.zjy.niklauslibrary.util.ImageLightUtils;
import com.example.zjy.niklauslibrary.util.ImageViewFit;
import com.example.zjy.niklauslibrary.util.TimeUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zjy on 2017/1/11.
 */

public class FeedSectionLayout extends FrameLayout {
    @Bind(R.id.iv_pic)
    ImageView iv_pic;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_category_video_duration)
    TextView tv_category_video_duration;
    @Bind(R.id.tv_author_name)
    TextView tv_author_name;
    @Bind(R.id.ll_content)
    LinearLayout ll_content;
    public FeedSectionLayout(Context context) {
        super(context);
        init();
    }

    public FeedSectionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FeedSectionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_rv_feed_section_single_iv,this,true);
        ButterKnife.bind(this);
    }

    public void setData(FeedBean.SectionListBean.ItemListBean itemListBean){
        FontHelper.injectFont(tv_title);
        String time = TimeUtils.secToTime(itemListBean.getData().getDuration());
        ImageLightUtils.changeLight(iv_pic,-64);
        tv_title.setText(itemListBean.getData().getTitle());
        tv_category_video_duration.setText( "#" + itemListBean.getData().getCategory() + "  " + "/" + "  " + time);
        //动态设置imageview的宽高
        ImageViewFit.dynamicImage(itemListBean.getData().getCover().getDetail(), iv_pic,getContext());
        if (itemListBean.getData().getAuthor() != null) {
            tv_author_name.setText(itemListBean.getData().getAuthor().getName());
        } else {
            tv_author_name.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                ll_content.animate()
                        .alphaBy(1)
                        .alpha(0)
                        .setDuration(100);
                ImageLightUtils.changeLight(iv_pic,-32);
                break;
            case MotionEvent.ACTION_UP:
                ll_content.animate()
                        .alphaBy(0)
                        .alpha(1)
                        .setDuration(100);
                ImageLightUtils.changeLight(iv_pic,-64);
                break;
        }
        return super.onTouchEvent(event);
    }
}
