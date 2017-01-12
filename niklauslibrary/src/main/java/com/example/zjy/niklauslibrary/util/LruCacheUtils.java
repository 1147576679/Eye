package com.example.zjy.niklauslibrary.util;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by zjy on 2016/10/9.
 */
public class LruCacheUtils {
    //Runtime.getRuntime().maxMemory() 内存的最大值
    private static LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>((int) (Runtime.getRuntime().maxMemory()/8)){
        //统一单位：总内存使用的单位是字节，sizeOf返回值必须是以字节为单位
        //回调方法sizeOf（），用来计算一次缓存数据的大小
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    };

    private static LruCache<String,String> lruCache2 = new LruCache<String,String>((int) (Runtime.getRuntime().maxMemory()/8)){
        @Override
        protected int sizeOf(String key, String value) {
            return value.length();
        }
    };

    public static void putCache(String url,Bitmap bitmap){
        String key = Md5Util.MD5(url);
        lruCache.put(key,bitmap);
    }

    public static Bitmap getCache(String url){
        String key = Md5Util.MD5(url);
        return lruCache.get(key);
    }

    public static void putJsonCache(String key,String json){
        lruCache2.put(key,json);
    }

    public static String getJsonCache(String key){
        return lruCache2.get(key);
    }

}
