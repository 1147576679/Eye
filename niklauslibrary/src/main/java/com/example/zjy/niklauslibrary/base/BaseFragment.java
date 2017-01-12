package com.example.zjy.niklauslibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by zjy on 2016/11/12.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentId(), null);
      /*  BaseActivity activity = (BaseActivity) getActivity();
        if((activity).isOpenTranslucent()){
            View id = view.findViewById(R.id.actionbar);
            if(id != null){
                int statusHeight = ScreenUtil.getStatusHeight(getActivity());
                Log.i("tag", "onCreateView: "+statusHeight);
                if(statusHeight != -1){
                    id.setPadding(0,statusHeight,0,0);
                }
            }
        }*/

        return view;
    }

    /**
     * 紧跟onCreateView创建
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
//        loadDatas();
        init(view);
        bindListener();
        Bundle bundle = getArguments();
        if(bundle != null){
            getDatas(bundle);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    protected void getDatas(Bundle bundle) {
    }

    protected void init(View view) {
    }

    protected void bindListener() {
    }

    protected void loadDatas() {
    }

    public abstract int getContentId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
