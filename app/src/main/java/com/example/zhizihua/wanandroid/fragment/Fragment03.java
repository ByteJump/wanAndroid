package com.example.zhizihua.wanandroid.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.zhizihua.wanandroid.ApiAdress;
import com.example.zhizihua.wanandroid.Bean.GongZhongHaoBean;
import com.example.zhizihua.wanandroid.R;
import com.example.zhizihua.wanandroid.fragment.adapter.TagFragmentAdapter;
import com.example.zhizihua.wanandroid.utils.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class Fragment03 extends BaseFragment {
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout tab;
    private ArrayList<GongZhongHaoBean.DataBean> datas = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    //自定义视图的全局变量
    private TextView tv_tab;
    @Override
    protected void getData() {
        loaddata();
    }

    private void loaddata() {
        OkGo.<GongZhongHaoBean>get(ApiAdress.GongZhongHao)
                .cacheKey("fragment03")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new JsonCallback<GongZhongHaoBean>(GongZhongHaoBean.class) {
                    @Override
                    public void onSuccess(Response<GongZhongHaoBean> response) {
                        if (response.body() != null) {
                            datas.clear();
                            datas.addAll(response.body().getData());
                            titles.clear();
                            for (int i = 0; i < datas.size(); i++) {
                                titles.add(datas.get(i).getName());
                                GongZhongHaoFragment fragment = new GongZhongHaoFragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", datas.get(i).getId());
                                fragment.setArguments(bundle);
                                fragments.add(fragment);
                            }
                            mViewPager.setAdapter(new TagFragmentAdapter(getFragmentManager(), fragments, titles));
                            tab.setupWithViewPager(mViewPager);
                            initTab();
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<GongZhongHaoBean> response) {
                        onSuccess(response);
                    }

                    @Override
                    public void onError(Response<GongZhongHaoBean> response) {
                        super.onError(response);
                        Log.e("fragment03", "55555");
                    }
                });
    }

    private void initTab() {
        for (int i = 0; i < titles.size(); i++) {
            //获取每一个tab对象
            TabLayout.Tab tabAt = tab.getTabAt(i);
            //将每一个条目设置我们自定义的视图
            tabAt.setCustomView(R.layout.tabtext);
            //默认选中第一个
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tabAt.getCustomView().findViewById(R.id.tv_tab).setSelected(true);//第一个tab被选中
                //设置选中标签的文字大小
                ((TextView) tabAt.getCustomView().findViewById(R.id.tv_tab)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            }
            //通过tab对象找到自定义视图的ID
            TextView textView = (TextView) tabAt.getCustomView().findViewById(R.id.tv_tab);
            textView.setText(titles.get(i));//设置tab上的文字
        }
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //定义方法，判断是否选中
                updateTabView(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //定义方法，判断是否选中
                updateTabView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void init() {
    }
    private void updateTabView(TabLayout.Tab tab, boolean isSelect) {
        //找到自定义视图的控件ID
        tv_tab = tab.getCustomView().findViewById(R.id.tv_tab);
        if(isSelect) {
            //设置标签选中
            tv_tab.setSelected(true);
            //选中后字体变大
            tv_tab.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

        }else{
            //设置标签取消选中
            tv_tab.setSelected(false);
            //恢复为默认字体大小
            tv_tab.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

        }
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment03;
    }
}
