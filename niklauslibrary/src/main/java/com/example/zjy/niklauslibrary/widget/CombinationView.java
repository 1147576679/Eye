package com.example.zjy.niklauslibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.zjy.niklauslibrary.R;


/**
 * Created by zjy on 2016/10/17.
 * 组合控件的使用
 * 1.先创建一个layout Xml文件，将要组合起来的自定义view放进去
 * 2.创建一个（组合）类继承该Xml布局
 * 3.在要使用组合控件的时候调用
 */
public class CombinationView extends FrameLayout implements SlideBar.OnTouchSlideBarListener {
    private SlideBar slideBar;
    private LabelView labelView;
    private ChooseWord chooseWord;
    public void setChooseWord(ChooseWord chooseWord){
        this.chooseWord = chooseWord;
    }
    public CombinationView(Context context) {
        this(context, null);
    }

    public CombinationView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public CombinationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.combination,this,true);
        slideBar = (SlideBar) findViewById(R.id.slideBar);
        slideBar.setOnTouchSlideBarListener(this);
        labelView = (LabelView) findViewById(R.id.lv);
    }

    @Override
    public void select(String content, int position) {
        labelView.setContent(content);
        if(chooseWord != null){
            chooseWord.chooseWordInfo(content,position);
        }
    }

    @Override
    public void unTouch() {
        labelView.setContent(null);
    }

    public interface ChooseWord{
        void chooseWordInfo(String word,int position);
    }
}
