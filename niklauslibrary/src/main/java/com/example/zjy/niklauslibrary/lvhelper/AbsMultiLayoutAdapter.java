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
 * Created by zjy on 2016/11/14.
 * T extends AbsMultiLayoutAdapter.OnMultiLayoutType
 */
public abstract class AbsMultiLayoutAdapter<T extends AbsMultiLayoutAdapter.OnMultiLayoutType> extends BaseAdapter {
    private Context context;
    private List<T> datas;
    private int[] resid;

    public AbsMultiLayoutAdapter(Context context,int... resid){
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
            convertView = LayoutInflater.from(context).inflate(resid[getItemViewType(position)],null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        bindView(viewHolder,datas.get(position),getItemViewType(position));
        return convertView;
    }


    public abstract void bindView(ViewHolder viewHolder, T data, int viewType);

    class ViewHolder{
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
//            Log.i("tag", "---->: "+value);
            TextView textView = (TextView) this.getView(id);
            textView.setText(value);
            return this;
        }
        public ViewHolder setImageView(int id,String url,int defaultid){
            ImageView imageView = (ImageView) this.getView(id);
            Glide.with(context)
                    .load(url)
                    .crossFade(500)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(defaultid)
                    .into(imageView);
            return this;
        }

    }

    @Override
    public int getItemViewType(int position) {
        //datas里面数据是OnMultiLayoutType的实现类
        OnMultiLayoutType onMultiLayoutType = datas.get(position);
        return onMultiLayoutType.getType();
    }

    @Override
    public int getViewTypeCount() {
        return resid.length;
    }

    /**
     * NewEntity实现该接口将type值返回，再根据type的值 就知道getItemViewType(int position)的type值
     */
    public interface OnMultiLayoutType{
        int getType();
    }
}
