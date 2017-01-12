package com.example.zjy.niklauslibrary.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by zjy on 2016/10/11.
 */
public class DiskLruCacheUtil {
    private static DiskLruCache diskLruCache;
    private static int maxSize = 1024 * 1024 *50;
    public static void init(Context context){
        /**
         * 参数1：硬盘缓存路径
         * 参数2：设置App版本号，版本号升级，则硬盘缓存自动清空
         * 参数3: 表示一个可以最多对应多少个value
         * 参数4：最大可以缓存的数据
         */
        if(diskLruCache == null) {
            try {
                diskLruCache = DiskLruCache.open(
                        getCacheFile(context, "banTang"),
                        getAppVersion(context),
                        1,
                        maxSize
                );
                Log.i("tag", "初始化了diskLruCache ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean putJsonCache(String url,String json){
        if(url != null && json != null && getCache(url) == null){
            String key = Md5Util.MD5(url);
            try {
                DiskLruCache.Editor writeCache = diskLruCache.edit(key);
                OutputStream outputStream = writeCache.newOutputStream(0);
                outputStream.write(json.getBytes());
                writeCache.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //读取缓存
    public static String getJsonCache(String url){
        if(url != null) {
            StringBuffer stringBuffer = new StringBuffer();
            String key = Md5Util.MD5(url);
            DiskLruCache.Snapshot snapshot;
            InputStream inputStream = null;
            BufferedReader in = null;
            try {
                snapshot = diskLruCache.get(key);
                if(snapshot != null) {
                    inputStream = snapshot.getInputStream(0);
                    in = new BufferedReader(new InputStreamReader(inputStream));
                    String str;
                    while ((str = in.readLine()) != null){
                        stringBuffer.append(str);
                    }
                    return stringBuffer.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    in.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //写入缓存
    public static boolean putCache(String url ,Bitmap bitmap){
        if(url != null && bitmap != null && getCache(url) == null) {
            try {
                String key = Md5Util.MD5(url);
                DiskLruCache.Editor writeCache = diskLruCache.edit(key);
                OutputStream outputStream = writeCache.newOutputStream(0);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                writeCache.commit();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    //读取缓存
    public static  Bitmap getCache(String url){
        if(url != null) {
            String key = Md5Util.MD5(url);
            DiskLruCache.Snapshot snapshot;
            try {
                snapshot = diskLruCache.get(key);
                if(snapshot != null) {
                    InputStream inputStream = snapshot.getInputStream(0);
                    return BitmapFactory.decodeStream(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //移除缓存
    public void removeCache(String url){
        try {
            String key = Md5Util.MD5(url);
            diskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //清空缓存
    public void cleanCache(){
        try {
            diskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //同步日志文件（diskLruCache会自动生成的文件）
    public static  void flushCache(){
        try {
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeCache(){
        try {
            diskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static File getCacheFile(Context context,String endPath){
        String cachePath;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            cachePath = context.getExternalCacheDir().getAbsolutePath();
        }else{
            cachePath = context.getCacheDir().getAbsolutePath();
        }

        File cacheFile = new File(cachePath,endPath);
        if(!cacheFile.exists()){
            cacheFile.mkdirs();
        }
        return cacheFile;
    }

    public static  int getAppVersion(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
