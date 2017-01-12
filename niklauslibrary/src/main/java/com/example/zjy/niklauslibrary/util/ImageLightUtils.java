package com.example.zjy.niklauslibrary.util;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;

/**
 * Created by zjy on 2017/1/6.
 * 处理图片的亮度
 */

public class ImageLightUtils {
    public static void changeLight(ImageView imageView, int brightness) {
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness,// 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
        imageView.setColorFilter(new ColorMatrixColorFilter(cMatrix));
    }
}
