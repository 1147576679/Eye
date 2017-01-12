package com.example.zjy.niklauslibrary.widget.vpindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zjy.niklauslibrary.R;
import com.example.zjy.niklauslibrary.widget.loopviewpager.LoopViewPager;


/**
 * Created by ken on 2016/12/28.
 * 配合ViewPager滑动的导航控件
 */
public class TabView extends LinearLayout {

    //导航图标的数量
    private int count;
    //被选中的资源
    private int checkedImg;
    //未被选中的资源
    private int unCheckedImg;

    private LayoutParams layoutParams;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //当前的线性布局默认为横向
        this.setOrientation(LinearLayout.HORIZONTAL);
        //获取自定义属性
        paresAttrs(attrs);
    }

    /**
     * 获取自定义属性
     * @param attrs
     */
    private void paresAttrs(AttributeSet attrs) {
        if(attrs != null){
            TypedArray typedArray = getContext().getResources().obtainAttributes(attrs, R.styleable.TabView);
            checkedImg = typedArray.getResourceId(R.styleable.TabView_checkdrawable, R.mipmap.ic_x_recycler_view_pager_indicator_focus);
            unCheckedImg = typedArray.getResourceId(R.styleable.TabView_uncheckdrawable, R.mipmap.ic_x_recycler_view_pager_indicator);
            typedArray.recycle();//回收资源
        }
    }

    /**
     * 由外界调用，传递导航图标的数量
     * @param count
     */
    public void setCount(int count){
        this.count = count;
        
        init();
    }

    private void init() {
        //清空当前的线性布局
        this.removeAllViews();

        layoutParams = new LayoutParams(30, 30);
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;

        for(int i = 0; i < count; i++){
            ImageView iv = new ImageView(getContext());
            if(i == 0){
                iv.setImageResource(checkedImg);
                iv.setTag("checked");
            } else {
                iv.setImageResource(unCheckedImg);
            }
            this.addView(iv, layoutParams);
        }
    }

    /**
     * 外界调用，设置和这个导航控件联动的ViewPager对象
     * @param viewPager
     */
    public void setViewPager(ViewPager viewPager){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = LoopViewPager.toRealPosition(position, count);

                //将原来被选中的资源换成未被选中的资源
                ImageView iv = (ImageView) findViewWithTag("checked");
                if(iv != null){
                    iv.setImageResource(unCheckedImg);
                    iv.setTag(null);
                }

                //将position处的资源换成被选中的资源
                ImageView iv2 = (ImageView) getChildAt(position);
                if(iv2 != null) {
                    iv2.setImageResource(checkedImg);
                    iv2.setTag("checked");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
