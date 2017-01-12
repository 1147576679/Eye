package com.example.zjy.niklauslibrary.util;

import android.graphics.Bitmap;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;


public interface ApiService {

    String BASE_URL = "http://ikft.house.qq.com/";

    @GET
    Observable<String> getJSON(@Url String url);
    @GET
    Observable<Bitmap> getBitmap(@Url String url);
}
