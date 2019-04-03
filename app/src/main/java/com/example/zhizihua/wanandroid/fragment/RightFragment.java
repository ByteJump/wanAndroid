package com.example.zhizihua.wanandroid.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zhizihua.wanandroid.Bean.NavagationBean;
import com.example.zhizihua.wanandroid.CheckListener;
import com.example.zhizihua.wanandroid.R;
import com.example.zhizihua.wanandroid.View.ItemHeaderDecoration;
import com.example.zhizihua.wanandroid.WebActivity;
import com.example.zhizihua.wanandroid.fragment.adapter.NavagationRightAdapter;
import com.example.zhizihua.wanandroid.fragment.adapter.RvAdapter;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhizihua on 2019/4/1.
 */


public class RightFragment extends BaseFragment implements CheckListener {
    @BindView(R.id.rv_city)
    RecyclerView recyclerView;
    public boolean move = false;
    private int moveCounts;
    private LinearLayoutManager linearLayoutManager;
    private CheckListener checkListener;
    private NavagationRightAdapter navagationRightAdapter;
    private ArrayList<NavagationBean.DataBean.TitlesBean> rightdata = new ArrayList<>();
    private ArrayList<NavagationBean.DataBean> leftdata = new ArrayList();

    @Override
    protected void getData() {

    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        leftdata = (ArrayList<NavagationBean.DataBean>) bundle.getSerializable("data");
        rightdata.clear();
        for (int i = 0; i < leftdata.size(); i++) {
            NavagationBean.DataBean.TitlesBean bean = new NavagationBean.DataBean.TitlesBean();
            bean.setIstitle(true);
            bean.setTag(String.valueOf(i));
            bean.setTitleName(leftdata.get(i).getName());
            rightdata.add(bean);
            for (int j = 0; j < leftdata.get(i).getArticles().size(); j++) {
                leftdata.get(i).getArticles().get(j).setTag(String.valueOf(i));
                rightdata.add(leftdata.get(i).getArticles().get(j));
            }
        }
        linearLayoutManager = new LinearLayoutManager(getActivity());
//        gridLayoutManager = new GridLayoutManager(getContext(), 3);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return rightdata.get(position).isIstitle() ? 3 : 1;
//            }
//        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerViewListener());
        navagationRightAdapter = new NavagationRightAdapter(getActivity(), rightdata, new RvAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                ToastUtils.show(rightdata.get(position).getLink());
                if(!rightdata.get(position).isIstitle()){
                    Intent intent = new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", rightdata.get(position).getLink());
                    startActivity(intent);
                }
            }
        });
        ItemHeaderDecoration decoration=new ItemHeaderDecoration(getActivity(),rightdata,leftdata);
        decoration.setData(rightdata);
        recyclerView.addItemDecoration(decoration);
        decoration.setCheckListener(checkListener);
        recyclerView.setAdapter(navagationRightAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.rightfragment;
    }

    public void setCheck(CheckListener listener) {
        this.checkListener = listener;
    }

    /*
         获取左边点击，右边滑动的距离
         */
    public void setCounts(int counts) {
        moveCounts = counts;
        moveToPosition(moveCounts);
        Log.i("<<<<<<", "count:" + moveCounts);
    }

    /*
    移动到指定位置
     */
    private void moveToPosition(int moveCounts) {
        int firstItem = linearLayoutManager.findFirstVisibleItemPosition();//获取屏幕可见的第一个item的position
        int lastItem = linearLayoutManager.findLastVisibleItemPosition();//获取屏幕可见的最后一个item的position
        if (moveCounts < firstItem) {
            recyclerView.scrollToPosition(moveCounts);
        } else if (moveCounts < lastItem) {
            View aimsView = recyclerView.getChildAt(moveCounts - firstItem);
            int top = aimsView.getTop();
            recyclerView.scrollBy(0, top);
        } else {
            /*
            当往下滑动的position大于可见的最后一个item的时候，调用 recyclerView.scrollToPosition(moveCounts);
            只能讲item滑动到屏幕的底部。
             */
            /*
            第一种方案：先将item移动到底部，然后在调用scrollBy移动到顶部。不可行，不能讲item滑动到顶部，
            离上面还有一小段距离；
             recyclerView.scrollToPosition(moveCounts);
            int top=recyclerView.getHeight();
            recyclerView.scrollBy(0,top);

            第二种方案：直接计算要滑动的距离。程序崩溃，报空指针。看系统源码可知，当
            滑动的距离大于ChildCount（可见的item数目），将返回空。
            int top=recyclerView.getChildAt(moveCounts-firstItem).getTop();
            recyclerView.scrollBy(0,top);

            第三种解决方案：先将目标item滑动到底部，然后进行异步处理。调用滚动监听方法RecyclerViewListener。
            滑动到顶部

             */

//            int top=recyclerView.getHeight();
//            recyclerView.scrollBy(0,top);
//            int childcount=recyclerView.getChildCount();
//            Log.i("<<<<<<<<<<","childcount"+childcount);
//            int top=recyclerView.getChildAt(moveCounts-firstItem).getTop();
//            recyclerView.scrollBy(0,top);

            recyclerView.scrollToPosition(moveCounts);
            move = true;
        }
    }

    @Override
    public void check(int position, boolean isScroll) {
        checkListener.check(position, isScroll);
    }

    private class RecyclerViewListener extends RecyclerView.OnScrollListener {
        /*
        监听回调，滑动结束回调。
         */
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //在这里进行第二次滚动（最后的100米！）
            if (move) {
                move = false;
                //获取要置顶的项在当前屏幕的位置，moveCount是记录的要置顶项在RecyclerView中的位置
                int n = moveCounts - linearLayoutManager.findFirstVisibleItemPosition();
                if (0 <= n && n < recyclerView.getChildCount()) {
                    //获取要置顶的项顶部离RecyclerView顶部的距离
                    int top = recyclerView.getChildAt(n).getTop();
                    //最后的移动
                    recyclerView.scrollBy(0, top);
                }
            }
        }

        /*
        监听回调，滑动状态改变回调
         */
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                move = false;
                int n = moveCounts - linearLayoutManager.findFirstVisibleItemPosition();
                if (0 <= n && n < recyclerView.getChildCount()) {
                    int top = recyclerView.getChildAt(n).getTop();
                    recyclerView.scrollBy(0, top);
                }
            }
        }
    }
}
