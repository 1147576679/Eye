package com.example.zjy.niklauslibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by zjy on 2016/11/12.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    //当前显示的fragment
    private BaseFragment showFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        //注册activity
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
       /* if(isOpenTranslucent()){
            *//**
             * 沉浸式状态栏设置
             *//*
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            //找到指定的控件，设置paddingTop为状态栏的高度（如果找到actionbar的控件，则设置）
            View view = findViewById(R.id.actionbar);
            if(view != null){
                int statusHeight = ScreenUtil.getStatusHeight(this);
                if(statusHeight != -1){
                    view.setPadding(0,statusHeight,0,0);
                    if(view instanceof Toolbar){
                        setSupportActionBar((Toolbar) view);

                        //隐藏标题
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                    }
                }
            }
        }*/
        init();
        bindListener();
        loadDatas();
    }

    /**
     * 初始化
     */
    protected void init() {

    }

    /**
     * toolbar初始化
     */
//    protected void setToolbar(Toolbar toolbar){
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
    /**
     * 绑定监听
     */
    protected  void bindListener(){

    }

    /**
     * 加载数据
     */
    protected void loadDatas(){

    }

    /**
     * 展示fragment的封装
     * @param resid
     * @param baseFragment
     */
    protected void showFragment(int resid, BaseFragment baseFragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //隐藏展示的Fragment
        if(showFragment != null){
            fragmentTransaction.hide(showFragment);
        }
        //展示需要显示的Fragment对象
        BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(baseFragment.getClass().getName());
        if(fragment != null){
            //如果不为空则显示
            fragmentTransaction.show(fragment);
            //将当前显示的加入showFragment中
            showFragment = fragment;
        }else{
            //为空则添加，将自己的Class.name作为标记
            fragmentTransaction.add(resid,baseFragment,baseFragment.getClass().getName());
            showFragment = baseFragment;
        }
        //最后提交事务
        fragmentTransaction.commit();
    }

    /**
     * 获得activity显示布局ID
     * @return
     */
    public abstract int getContentId();

    /**
     * 过场动画
     * @param intent
     * @param animIn
     * @param animOut
     */
    public void startActivityForAnimation(Intent intent,int animIn, int animOut){
        startActivity(intent);
        overridePendingTransition(animIn,animOut);
    }

    /**
     * 是否打开沉浸式状态栏
     * @return
     */
    protected boolean isOpenTranslucent(){
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
