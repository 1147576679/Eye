package com.example.zjy.niklauslibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zjy on 2016/9/3.
 */
public class ShareUtils {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static void setPutString(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public static String getPutString(String key){
        return sharedPreferences.getString(key,null);
    }
    public static void setIntString(String key,int value){
        editor.putInt(key,value);
        editor.commit();

    }
    public static int getIntString(String key){
        return sharedPreferences.getInt(key,0);
    }
    public static void setPutBoolean(String key,boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }
    public static boolean getPutBoolean(String key){
     return sharedPreferences.getBoolean(key,false);
    }
}
