package com.example.zjy.niklauslibrary.util;

import android.content.Context;

/**
 * Created by zjy on 2016/12/26.
 */

public class ScreenUtil {
    /**
     * 得到状态栏的高度
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context){
        int resid = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resid > 0){
            return context.getResources().getDimensionPixelSize(resid);//通过资源id获得资源对应的值
        }
        return -1;
    }

    public static float getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static float getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
