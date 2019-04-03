package com.example.zhizihua.wanandroid.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhizihua on 2019/3/25.
 */

public abstract class BaseFragment extends Fragment{
    private ProgressDialog progress;
    private boolean isFirstLoad = false;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),container,false);
        ButterKnife.bind(this,view);
        unbinder = ButterKnife.bind(this,view);
        isFirstLoad = true;//视图创建完成，将变量置为true
        if (getUserVisibleHint()) {//如果Fragment可见进行数据加载
            getData();
            isFirstLoad = false;
        }
        init();
        return view;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isFirstLoad && isVisibleToUser) {//视图变为可见并且是第一次加载
            getData();
            isFirstLoad = false;
        }

    }
    protected abstract void getData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected abstract void init();

    protected abstract int getLayout();

    public void showProgress(){
        progress = new ProgressDialog(getActivity());
        progress.setMessage("加载中……");
        progress.setCancelable(true);
        if(progress != null){
            progress.show();
        }
    }
    public void hideProgress(){
        if (progress != null)
            if (progress.isShowing()) {
                progress.dismiss();
            }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isFirstLoad = false;//视图销毁将变量置为false
    }
}
