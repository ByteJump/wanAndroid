package com.example.zhizihua.wanandroid;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

/**
 * Created by zhizihua on 2019/3/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        getData();
        init();
    }

    protected abstract void getData();

    protected abstract void init();

    protected abstract int getLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
