package com.example.zhizihua.wanandroid.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zhizihua.wanandroid.ApiAdress;
import com.example.zhizihua.wanandroid.Bean.ArticleBean;
import com.example.zhizihua.wanandroid.Bean.BannerBean;
import com.example.zhizihua.wanandroid.R;
import com.example.zhizihua.wanandroid.SearchActivity;
import com.example.zhizihua.wanandroid.WebActivity;
import com.example.zhizihua.wanandroid.fragment.adapter.ArticleListAdapter;
import com.example.zhizihua.wanandroid.utils.JsonCallback;
import com.google.gson.Gson;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class Fragment01 extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.titlebar)
    TitleBar titleBar;
    private ArrayList<ArticleBean.DataBean.DatasBean> datas = new ArrayList<>();
    private ArrayList<BannerBean.DataBean> bannerdata = new ArrayList<>();
    private ArticleListAdapter articleListAdapter;
    private int page = 0;
    private String TAG = "Fragment01";
    private String URL;

    @Override
    protected void init() {
        articleListAdapter = new ArticleListAdapter(getActivity(), datas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(articleListAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 0;
                load(refreshlayout);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page = page + 1;
                load(refreshlayout);
            }
        });
        articleListAdapter.setOnitemClickLintener(new ArticleListAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", datas.get(position).getLink());
                startActivity(intent);
            }
        });
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {


            @Override
            public void onLeftClick(View v) {

            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        });
    }

    protected void getData() {
        load(null);
        getBannerData();
    }

    private void getBannerData() {
        OkGo.<String>get(ApiAdress.Banner).execute(new AbsCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, response.body().toString());
                Gson gson = new Gson();
                BannerBean bannerBean = gson.fromJson(response.body().toString(), BannerBean.class);
                bannerdata.clear();
                bannerdata.addAll(bannerBean.getData());
                articleListAdapter.setBannerdata(bannerdata);
//                articleListAdapter.notifyDataSetChanged();
            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                return response.body().string();
            }
        });
    }

    private void load(final RefreshLayout refreshlayout) {
        URL = ApiAdress.ArticleList + page + "/json";
        OkGo.<ArticleBean>get(URL).execute(new JsonCallback<ArticleBean>(ArticleBean.class) {
            @Override
            public void onSuccess(Response<ArticleBean> response) {
//                Log.e("55555", response.body().getData().getDatas().size() + "    666");
            }
        });
        OkGo.<String>get(URL).execute(new AbsCallback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                if (refreshlayout == null)
                    showProgress();
                Log.e(TAG, "start");
            }

            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, response.body().toString());
                if (refreshlayout != null && page == 0) {
                    refreshlayout.finishRefresh();
                }
                if (refreshlayout != null && page != 0) {
                    refreshlayout.finishLoadMore();
                }
                Gson gson = new Gson();
                ArticleBean articleBean = gson.fromJson(response.body().toString(), ArticleBean.class);
                if (page == 0) datas.clear();
                datas.addAll(articleBean.getData().getDatas());
                articleListAdapter.notifyDataSetChanged();
            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                return response.body().string();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (refreshlayout == null)
                    hideProgress();
                Log.e(TAG, "finish");
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment01;
    }
}





















