package com.example.zhizihua.wanandroid.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

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

    @Override
    protected void init() {
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment03;
    }
}
