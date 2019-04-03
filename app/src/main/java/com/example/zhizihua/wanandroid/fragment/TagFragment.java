package com.example.zhizihua.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zhizihua.wanandroid.ApiAdress;
import com.example.zhizihua.wanandroid.Bean.KnowledgeItemBean;
import com.example.zhizihua.wanandroid.R;
import com.example.zhizihua.wanandroid.fragment.adapter.KnowledgeItemAdapter;
import com.example.zhizihua.wanandroid.utils.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by zhizihua on 2019/3/28.
 */

public class TagFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;
    private KnowledgeItemAdapter adapter;
    private int id;
    private int page = 0;
    private ArrayList<KnowledgeItemBean.DataBean.DatasBean> datas = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
        }
    }

    @Override
    protected void getData() {
        loaddata(page, null);
    }

    private void loaddata(final int page, final RefreshLayout refreshLayout) {
        String URL = ApiAdress.Knowledgeitem + page + "/json?" + "cid=" + id;
        OkGo.<KnowledgeItemBean>get(URL).execute(new JsonCallback<KnowledgeItemBean>(KnowledgeItemBean.class) {
            @Override
            public void onStart(Request<KnowledgeItemBean, ? extends Request> request) {
                super.onStart(request);
                if (refreshLayout == null)
                    showProgress();
            }
            @Override
            public void onSuccess(Response<KnowledgeItemBean> response) {
                if (response.body() != null) {
                    if (page == 0) datas.clear();
                    datas.addAll(response.body().getData().getDatas());
                    adapter.notifyDataSetChanged();
                }
                if (refreshLayout != null && page == 0) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout != null && page != 0) {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (refreshLayout == null)
                    hideProgress();
            }

            @Override
            public void onError(Response<KnowledgeItemBean> response) {
                super.onError(response);
                Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                if (refreshLayout != null) refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected void init() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new KnowledgeItemAdapter(datas, getActivity());
        recyclerview.setAdapter(adapter);
        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                loaddata(page, refreshLayout);
            }
        });
        smartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = page + 1;
                loaddata(page, refreshLayout);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_tag;
    }
}
