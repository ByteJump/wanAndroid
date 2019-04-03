package com.example.zhizihua.wanandroid.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.just.agentweb.LogUtils;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by zhizihua on 2019/3/26.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Type type;
    private Class<T> clazz;

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
//        LogUtils.e("response",response.body().string());
        if (body == null) return null;

        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) data = gson.fromJson(jsonReader, type);
        if (clazz != null) data = gson.fromJson(jsonReader, clazz);
        return data;
    }
}
