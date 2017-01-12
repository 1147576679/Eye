package com.example.zjy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.zjy.eye.R;

/**
 * Created by zjy on 2017/1/6.
 */

public class FeedHeadView extends FrameLayout {
    public FeedHeadView(Context context) {
        this(context, null);
    }

    public FeedHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FeedHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.feed_head_view,this,false);
    }
}
