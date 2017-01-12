package com.example.zjy.utils;

import com.example.zjy.bean.FeedBean;
import com.google.gson.Gson;

/**
 * Created by zjy on 2017/1/5.
 */

public class ParseUtils {
    /**
     * 解析feed fragment页面的数据
     * @param json
     * @return
     */
    public static FeedBean parseFeed(String json){
        return new Gson().fromJson(json,FeedBean.class);
    }
}
