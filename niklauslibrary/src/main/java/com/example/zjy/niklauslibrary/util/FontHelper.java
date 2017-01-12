package com.example.zjy.niklauslibrary.util;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *递归遍历子控件设置字体
 * com.example.ziyulibrary.Util
 * Created by ziyu on 2016/10/9 .
 */

public class FontHelper {
    public static final String FONTS_DIR = "fonts/";
    public static final String DEF_FONT = FONTS_DIR + "FZLanTingHeiS-DB1-GB-Regular.TTF";
    public static final String DEF_FONT2 = FONTS_DIR + "Lobster-1.4.otf";

    public static final void injectFont(View rootView){
        injectFont(rootView ,
                Typeface.createFromAsset(rootView.getContext().getAssets(),DEF_FONT));
    }

    public static final void injectFont(View rootView ,Typeface tf ){
        if(rootView instanceof ViewGroup){
            ViewGroup group = (ViewGroup)rootView;
            int count = group.getChildCount();
            for(int  i = 0; i<count;i++){
                injectFont(group.getChildAt(i),tf);
            }
        }else if(rootView instanceof TextView) {
            ((TextView) rootView).setTypeface(tf);
        }
    }

    public static final void injectFont2(View rootView){
        injectFont(rootView ,
                Typeface.createFromAsset(rootView.getContext().getAssets(),DEF_FONT2));
    }
}
