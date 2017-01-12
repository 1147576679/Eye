package com.example.zjy.niklauslibrary.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by zjy on 2016/12/12.
 */
public class ConvenientBannerUtils {
    private List<String> mlist;
    private int indicatorSelect;
    private int indicatorUnselect;
    private ConvenientBanner.PageIndicatorAlign orientation; //3个值 左中右
    private long time;
    private OnConvenientBannerListener onConvenientBannerListener;

    public List<String> getMlist() {
        return mlist;
    }

    public int getIndicatorSelect() {
        return indicatorSelect;
    }

    public int getIndicatorUnselect() {
        return indicatorUnselect;
    }

    public ConvenientBanner.PageIndicatorAlign getOrientation() {
        return orientation;
    }

    public long getTime() {
        return time;
    }

    public OnConvenientBannerListener getOnConvenientBannerListener(){
        return onConvenientBannerListener;
    }

    public ConvenientBannerUtils(Builder builder) {
        mlist = builder.mlist;
        indicatorSelect = builder.indicatorSelect;
        indicatorUnselect = builder.indicatorUnselect;
        orientation = builder.orientation;
        time = builder.time;
        onConvenientBannerListener = builder.onConvenientBannerListener;
    }

    public static class Builder{
        private List<String> mlist;
        private int indicatorSelect;
        private int indicatorUnselect;
        private ConvenientBanner.PageIndicatorAlign orientation; //3个值 左中右
        private long time;
        private OnConvenientBannerListener onConvenientBannerListener;

        public Builder(){
            this.mlist = null;
            this.indicatorSelect = 0;
            this.indicatorUnselect = 0;
            this.orientation = null;
            this.time = 0;
            this.onConvenientBannerListener = null;
        }

        public Builder mList(List<String> mlist){
            this.mlist = mlist;
            return this;
        }
        public Builder indicatorSelect(int indicatorSelect){
            this.indicatorSelect = indicatorSelect;
            return this;
        }
        public Builder indicatorUnselect(int indicatorUnselect){
            this.indicatorUnselect = indicatorUnselect;
            return this;
        }
        public Builder orientation(ConvenientBanner.PageIndicatorAlign orientation){
            this.orientation = orientation;
            return this;
        }
        public Builder time(long time){
            this.time = time;
            return this;
        }

        public Builder onConvenientBannerListener(OnConvenientBannerListener onConvenientBannerListener){
            this.onConvenientBannerListener = onConvenientBannerListener;
            return this;
        }

        public ConvenientBannerUtils build(){
            return new ConvenientBannerUtils(this);
        }
    }

    public void ConvenientBanner(ConvenientBanner convenientBanner){
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, mlist) //mList是图片地址的集合
                .setPointViewVisible(true)    //设置指示器是否可见
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{indicatorUnselect, indicatorSelect})
                //设置指示器位置（左、中、右）
                .setPageIndicatorAlign(orientation)
                .startTurning(time)     //设置自动切换（同时设置了切换时间间隔）
                .setManualPageable(true)  //设置手动影响（设置了该项无法手动切换）
        ;
    }

    class LocalImageHolderView implements Holder<String> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            //glide加载出图片，data是传过来的图片地址，
            Glide.with(context).load(data).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onConvenientBannerListener != null){
                        onConvenientBannerListener.onClick(position);
                    }
                }
            });
        }
    }

    public interface OnConvenientBannerListener{
        void onClick(int position);
    }
}
