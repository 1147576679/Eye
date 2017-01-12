package com.example.zjy.niklauslibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by zjy on 2017/1/11.
 */

public class ImageViewFit {
    public static void dynamicImage(String url, final ImageView iv_pic, final Context context) {
        Glide.with(context.getApplicationContext())
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        int width = resource.getWidth();
                        float height = resource.getHeight();
                        float widthPixels = ScreenUtil.getScreenWidth(context);
                        int mHeight = (int) ((height / width) * widthPixels);
                        ViewGroup.LayoutParams params = iv_pic.getLayoutParams();
                        params.width = (int) widthPixels;
                        params.height= mHeight;
                        iv_pic.setLayoutParams(params);
                        iv_pic.setImageBitmap(resource);
                    }
                });
    }
}
