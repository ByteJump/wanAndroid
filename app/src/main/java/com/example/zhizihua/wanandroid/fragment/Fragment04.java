package com.example.zhizihua.wanandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.zhizihua.wanandroid.ApiAdress;
import com.example.zhizihua.wanandroid.Bean.NavagationBean;
import com.example.zhizihua.wanandroid.CheckListener;
import com.example.zhizihua.wanandroid.R;
import com.example.zhizihua.wanandroid.fragment.adapter.NavagationLeftAdapter;
import com.example.zhizihua.wanandroid.fragment.adapter.RvAdapter;
import com.example.zhizihua.wanandroid.utils.JsonCallback;
import com.hjq.toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class Fragment04 extends BaseFragment implements CheckListener {
    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.fl_right)
    FrameLayout flRight;
    private NavagationLeftAdapter navagationLeftAdapter;
    private LinearLayoutManager manager;
    private RightFragment rightFragment;
    private List<NavagationBean.DataBean> leftdata = new ArrayList();
    private ArrayList<String> data = new ArrayList<>();
    private int mposition;

    @Override
    protected void getData() {
        OkGo.<NavagationBean>get(ApiAdress.Navagation)
                .cacheKey("fragment04")
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                .execute(new JsonCallback<NavagationBean>(NavagationBean.class) {
            @Override
            public void onStart(Request<NavagationBean, ? extends Request> request) {
                super.onStart(request);
                showProgress();
            }

            @Override
            public void onSuccess(Response<NavagationBean> response) {
                if (response.body() != null) {
                    leftdata.clear();
                    data.clear();
                    leftdata.addAll(response.body().getData());
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        data.add(response.body().getData().get(i).getName());
                    }
                    addRightData();
                }
                navagationLeftAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCacheSuccess(Response<NavagationBean> response) {
                ToastUtils.show("444");
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
        navagationLeftAdapter = new NavagationLeftAdapter(getActivity(), data, new RvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mposition = position;
                startMove(position, true);
                Log.i(">>>>>>", "position:" + position);
                moveToCenter(position);
            }
        });
        manager = new LinearLayoutManager(getActivity());
        rvLeft.setLayoutManager(manager);
        rvLeft.setAdapter(navagationLeftAdapter);
//        addRightData();
    }

    //将当前选中的item居中
    public void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        Log.i(">>>>>>>>>", position - manager.findFirstVisibleItemPosition() + "eeeee");
        int itemPosition = position - manager.findFirstVisibleItemPosition();
        /*
        当往上滑动太快，会出现itemPosition为-1的情况。做下判断
         */
        if (0 < itemPosition && itemPosition < manager.getChildCount()) {
            View childAt = rvLeft.getChildAt(position - manager.findFirstVisibleItemPosition());
            Log.i("<<<<<<", position - manager.findFirstVisibleItemPosition() + "");

            int y = (childAt.getTop() - rvLeft.getHeight() / 2);
            Log.i("<<<<<<", childAt.getTop() + "ssssss");
            Log.i("<<<<<<", y + "");
            rvLeft.smoothScrollBy(0, y);
        }

    }

    private void startMove(int position, boolean isLeft) {
        int counts = 0;
        for (int i = 0; i < position; i++)//position 为点击的position
        {
//            Log.i("<<<<<<",i+":"+rightFragment.rightdata.get(i).length);
            counts += leftdata.get(i).getArticles().size();//计算需要滑动的城市数目
        }
        if (isLeft) {
            rightFragment.setCounts(counts + position);//加上title（省份）数目
        } else {
//            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));
        }
        navagationLeftAdapter.setClickPositon(position);//设置点击的位置，改变省份点击背景
    }

    private void addRightData() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        rightFragment = new RightFragment();
        Bundle bundle = new Bundle();
        Log.e("sd45", leftdata.size() + "");
        bundle.putSerializable("data", (Serializable) leftdata);
        rightFragment.setArguments(bundle);
        rightFragment.setCheck(this);
        ft.add(R.id.fl_right, rightFragment);
        ft.commit();

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment04;
    }

    @Override
    public void check(int position, boolean isScroll) {
        startMove(position, isScroll);
        moveToCenter(position);
    }
}
