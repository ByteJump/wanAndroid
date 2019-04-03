package com.example.zhizihua.wanandroid.fragment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.zhizihua.wanandroid.R;

import java.util.List;

/**
 * Created by zhizihua on 2019/4/1.
 */

public class NavagationLeftAdapter extends RvAdapter<String>{
    private int clickPositon;
    public NavagationLeftAdapter(Context context, List<String> list, ItemClickListener listener) {
        super(context, list, listener);
    }

    public NavagationLeftAdapter() {
        super();
    }

    public  void setClickPositon(int position)
    {
        clickPositon=position;
        Log.i("SIMON","clickposition"+clickPositon);
        notifyDataSetChanged();//更新view，否则点击背景不换
    }
    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new NavagationLeftHolder(view,viewType,listener);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_leftnavagation;
    }
    class NavagationLeftHolder extends RvHolder<String>{
        private TextView textView;
        private View view;
        public NavagationLeftHolder(View itemView, int type, ItemClickListener listener) {
            super(itemView, type, listener);
            view=itemView;
            textView= view.findViewById(R.id.tv_navagation);
        }

        @Override
        public void bindHolder(String s, int position) {
            Log.i(">>>>>>","click"+clickPositon);
            if (position==clickPositon)
            {
//                view.setBackgroundColor(Color.parseColor("#FF0000"));
                textView.setTextColor(Color.parseColor("#FF0000"));
            }
            else {
                view.setBackgroundColor(Color.parseColor("#00FFFFFF"));//设置为透明的，因为白色会覆盖分割线
                textView.setTextColor(Color.parseColor("#1e1d1d"));
            }
            textView.setText(s);
        }
    }
}
