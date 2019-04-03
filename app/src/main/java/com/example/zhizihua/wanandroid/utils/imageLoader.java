package com.example.zhizihua.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class imageLoader {
    public static void loadImage(Context ctx, String url, ImageView iv) {
        Glide.with(ctx).load(url).into(iv);
    }
}
