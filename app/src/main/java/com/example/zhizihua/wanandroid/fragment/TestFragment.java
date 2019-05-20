package com.example.zhizihua.wanandroid.fragment;

import android.widget.Button;

import com.example.zhizihua.wanandroid.R;

import butterknife.BindView;

/**
 * Created by zhizihua on 2019/5/16.
 */

public class TestFragment extends BaseFragment{
    @BindView(R.id.btn)
    Button webbtn;
    @Override
    protected void getData() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_test;
    }
}
