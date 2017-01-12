package com.example.zjy.niklauslibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zjy on 2016/10/17.
 */
public class SlideBar extends View {
    private String[] contents = {
            "当前","热门",
            "A","B","C","D","E","F",
            "G","H","I","J","K","L",
            "M","N","O","P","Q","R",
            "S","T","U","V","W","X",
            "Y","Z"};
    private Paint textPaint;
    //每个字母的高度
    private int contentHeight;

    //点击SlideBar下标
    private int index = -1;
    private OnTouchSlideBarListener onTouchSlideBarListener;

    public void setOnTouchSlideBarListener(OnTouchSlideBarListener onTouchSlideBarListener) {
        this.onTouchSlideBarListener = onTouchSlideBarListener;
    }
    /**
     * new 一个对象时调用
     * @param context
     */
    public SlideBar(Context context) {
        this(context, null);
    }
    /**
     * 当自定义控件以标签的形式写在布局中时调用
     * @param context
     * @param attrs
     */
    public SlideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    /**
     * 当自定义控件以标签的形式写在布局时，并且包含一个style属性时调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.GRAY);
        textPaint.setTextSize(28);
        contentHeight = (int) (textPaint.descent() - textPaint.ascent());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < contents.length; i++) {
            if (i == index){
                textPaint.setColor(Color.parseColor("#39a6d9"));
            }else{
                textPaint.setColor(Color.GRAY);
            }
            canvas.drawText(
                    contents[i],
                    getWidth()/2 - textPaint.measureText(contents[i])/2,
                    (i + 1) * contentHeight,
                    textPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = measureWH(widthMeasureSpec, 1);
        int measureHeight = measureWH(heightMeasureSpec, 2);
        setMeasuredDimension(measureWidth,measureHeight);
    }

    /**
     * @param spec 宽度或者高度的空间值
     * @param type 1表示宽度，2表示高度
     */
    public int measureWH(int spec, int type){
        int size = MeasureSpec.getSize(spec);
        int mode = MeasureSpec.getMode(spec);
        switch (mode){
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.AT_MOST:
                switch (type){
                    case 1:
                        return (int) (textPaint.measureText(contents[0])+ getPaddingLeft() + getPaddingRight());
                    case 2:
                        return (contentHeight * contents.length) +getPaddingTop() + getPaddingBottom();
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        return size;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchE(event);
                break;
            case MotionEvent.ACTION_UP:
                index = -1;
                invalidate();
                if(onTouchSlideBarListener != null){
                    onTouchSlideBarListener.unTouch();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                touchE(event);
                break;
        }
        return true;
    }

    private void touchE(MotionEvent event){
        int y = (int) event.getY();
        int index = y/contentHeight;

        if(index < 0){
            index = 0;
        }
        if(index > contents.length -1){
            index = contents.length-1;
        }
        //防止从一个字母滑动到另一个字母一直调用invalidate（）重绘方法。
        if(this.index == index){
            return;
        }
        this.index = index;
        if(onTouchSlideBarListener != null){
            onTouchSlideBarListener.select(contents[index],index);
        }
        Log.i("tag", "onTouchEvent: " + this.index);
        invalidate();
    }
    public interface OnTouchSlideBarListener{
        void select(String content, int position);
        void unTouch();
    }
}
