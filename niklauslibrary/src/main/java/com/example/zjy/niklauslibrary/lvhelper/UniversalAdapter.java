package com.example.zjy.niklauslibrary.lvhelper;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjy on 2016/10/11.
 */
public abstract class UniversalAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> datas;
    private int resid;

    public UniversalAdapter(Context context,int resid){
        this.context = context;
        this.resid = resid;
        datas = new ArrayList<>();
    }

    public void setDatas(List<T> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public void addDatas(List<T> datas){
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(context).inflate(resid,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        bindView(viewHolder,datas.get(position));
        return convertView;
    }

    public abstract void bindView(ViewHolder viewHolder,T data);

    public class ViewHolder{
        SparseArray<View> sparseArray = new SparseArray<>();
        View layoutView;
        public ViewHolder(View layoutView){
            this.layoutView = layoutView;
        }

        public View getView(int id){
            View view = sparseArray.get(id);
            if(view == null) {
                view = layoutView.findViewById(id);
                sparseArray.put(id,view);
            }
            return view;
        }

        public ViewHolder setText(int id,String value){
            TextView textView = (TextView) this.getView(id);
            textView.setText(value);
            return this;
        }

        /**
         *
         * @param id
         * @param url
         * @param placeholder 占位图
         */
        public ViewHolder setImageUrl(int id,String url,int placeholder){
            ImageView imageView = (ImageView) this.getView(id);
            Glide.with(context)
                    .load(url)
                    .placeholder(placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            return this;
        }
    }
}

