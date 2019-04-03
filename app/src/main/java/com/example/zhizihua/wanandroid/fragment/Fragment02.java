package com.example.zhizihua.wanandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhizihua.wanandroid.ApiAdress;
import com.example.zhizihua.wanandroid.Bean.KnowledgeBean;
import com.example.zhizihua.wanandroid.KnowedgeDetailActivity;
import com.example.zhizihua.wanandroid.R;
import com.example.zhizihua.wanandroid.fragment.adapter.KnowledgeAdapter;
import com.example.zhizihua.wanandroid.utils.JsonCallback;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class Fragment02 extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    private KnowledgeAdapter adapter;
    private ArrayList<KnowledgeBean.DataBean> datas = new ArrayList<>();

    @Override
    protected void getData() {
        load(null);
    }

    private void load(final RefreshLayout refreshLayout) {
        OkGo.<KnowledgeBean>get(ApiAdress.KnowledgeList)
                .cacheKey("fragment02")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new JsonCallback<KnowledgeBean>(KnowledgeBean.class) {
                    @Override
                    public void onStart(Request<KnowledgeBean, ? extends Request> request) {
                        super.onStart(request);
                        showProgress();
                    }

                    @Override
                    public void onSuccess(Response<KnowledgeBean> response) {
                        if (response.body() != null) {
                            datas.clear();
                            datas.addAll(response.body().getData());
                            adapter.notifyDataSetChanged();
                            if (refreshLayout != null) {
                                refreshLayout.finishRefresh();
                            }
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<KnowledgeBean> response) {
                        ToastUtils.show("666");
                        onSuccess(response);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        hideProgress();
                    }
                });
    }

    @Override
    protected void init() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new KnowledgeAdapter(getActivity(), datas);
        recyclerview.setAdapter(adapter);
        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                load(refreshLayout);
            }
        });
        adapter.setOnItemClick(new KnowledgeAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), KnowedgeDetailActivity.class);
                List<KnowledgeBean.DataBean.ChildrenBean> children = datas.get(position).getChildren();
                Bundle bundle = new Bundle();
                bundle.putSerializable("ChildrenBeanlist", (Serializable) children);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment02;
    }

}
