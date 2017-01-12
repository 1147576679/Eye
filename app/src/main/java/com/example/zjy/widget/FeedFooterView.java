package com.example.zjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zjy.bean.FeedBean;
import com.example.zjy.eye.R;

/**
 * Created by zjy on 2017/1/6.
 */

public class FeedFooterView extends LinearLayout {
    private TextView tv_text;
    public FeedFooterView(Context context) {
        this(context, null);
    }

    public FeedFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.feed_footer_view,this,false);
        tv_text = (TextView) view.findViewById(R.id.tv_footer_text);
    }

    public void loadData(FeedBean.SectionListBean sectionListBean){
        tv_text.setText(sectionListBean.getFooter().getData().getText());
    }
}
