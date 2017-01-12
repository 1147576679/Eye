package com.example.zjy.niklauslibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zjy on 2016/10/17.
 */
public class LabelView extends View {
    private Paint rectPaint,textPaint;
    private String content;
    public LabelView(Context context) {
        this(context, null);
    }

    public LabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setColor(Color.parseColor("#39a6d9"));

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(28);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(content != null){
            canvas.drawRect(
                    getWidth()/2 - 30,
                    getHeight()/2 - 30,
                    getWidth()/2 + 30,
                    getHeight()/2 + 30,
                    rectPaint
            );
            canvas.drawText(
                    content,
                    getWidth()/2 - textPaint.measureText(content)/2,
                    getHeight()/2 + (textPaint.descent() - textPaint.ascent())/2 - textPaint.descent(),
                    textPaint
            );
        }else{
            this.setVisibility(GONE);
        }

    }

    public void setContent(String content){
        this.setVisibility(VISIBLE);
        this.content = content;
        invalidate();
    }
}
