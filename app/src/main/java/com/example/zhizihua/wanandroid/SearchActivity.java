package com.example.zhizihua.wanandroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.zhizihua.wanandroid.Bean.HotKeyBean;
import com.example.zhizihua.wanandroid.utils.JsonCallback;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhizihua on 2019/4/3.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.titlebar)
    TitleBar titlebar;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowLayout;
    private ArrayList<HotKeyBean.DataBean> data = new ArrayList<>();
    private Context context;
    Random random = new Random();
    @Override
    protected void getData() {
        OkGo.<HotKeyBean>get(ApiAdress.hotkey).execute(new JsonCallback<HotKeyBean>(HotKeyBean.class) {
            @Override
            public void onSuccess(Response<HotKeyBean> response) {
                if (response.body() != null) {
                    data.clear();
                    data.addAll(response.body().getData());
                    flowLayout.setAdapter(new TagAdapter<HotKeyBean.DataBean>(data) {
                        @Override
                        public View getView(FlowLayout parent, int position, HotKeyBean.DataBean dataBean) {
                            TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.tv_hotkey,
                                    flowLayout, false);
                            int r = 30 + random.nextInt(200);
                            int g = 30 + random.nextInt(200);
                            int b = 30 + random.nextInt(200);
                            tv.setTextColor(Color.rgb(r, g, b));
                            tv.setText(dataBean.getName());
                            return tv;
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void init() {
        context = this;
        initEvent();
    }

    private void initEvent() {
        titlebar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ToastUtils.show(data.get(position).getName());
                return true;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }
}
