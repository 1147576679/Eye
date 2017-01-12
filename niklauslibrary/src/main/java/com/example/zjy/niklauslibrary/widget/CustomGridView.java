package com.example.zjy.niklauslibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by zjy on 2016/11/26.
 */
public class CustomGridView extends GridView {
    public CustomGridView(Context context) {
        this(context, null);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

    @Override
    public int getNumColumns() {
        return 3;
    }
}
