package com.example.zjy.niklauslibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by zjy on 2016/11/30.
 */
public class CustomListView extends ListView {
    public CustomListView(Context context) {
        this(context, null);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
