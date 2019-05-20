package com.example.zhizihua.wanandroid;

import android.app.Application;

import com.hjq.toast.ToastUtils;
import com.just.agentweb.AgentWebConfig;
import com.lzy.okgo.OkGo;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        OkGo.getInstance().init(this);
        AgentWebConfig.DEBUG = true;
    }
}
