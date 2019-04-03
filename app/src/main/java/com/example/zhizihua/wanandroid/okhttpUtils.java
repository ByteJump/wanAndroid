package com.example.zhizihua.wanandroid;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhizihua on 2019/3/20.
 */

public class okhttpUtils {
    private static OkHttpClient okHttpClient = null;
    private static Handler mHandler;

    private static OkHttpClient getInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
            okHttpClient.newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS);
        }
        mHandler = new Handler(Looper.getMainLooper());
        return okHttpClient;
    }
    public static void get(String url){
        Request request = new Request.Builder().url(url).build();
        Call call = getInstance().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("asdfadf",response.toString());
            }
        });
    }
}
