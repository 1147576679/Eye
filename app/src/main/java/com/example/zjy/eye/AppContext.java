package com.example.zjy.eye;

import android.app.Application;

import com.example.zjy.niklauslibrary.util.ShareUtils;

/**
 * Created by zjy on 2017/1/5.
 */

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ShareUtils.init(this);
    }
}
