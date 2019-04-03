package com.example.zhizihua.wanandroid;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.zhizihua.wanandroid.Bean.KnowledgeBean;
import com.example.zhizihua.wanandroid.fragment.TagFragment;
import com.example.zhizihua.wanandroid.fragment.adapter.TagFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by zhizihua on 2019/3/28.
 */

public class KnowedgeDetailActivity extends BaseActivity {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private List<KnowledgeBean.DataBean.ChildrenBean> childrenBeanList;
    private ArrayList<String> titles = new ArrayList<>();
    private List<Integer> ids = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void getData() {

    }

    @Override
    protected void init() {
        childrenBeanList = (List<KnowledgeBean.DataBean.ChildrenBean>) getIntent().getSerializableExtra("ChildrenBeanlist");
        titles.clear();
        ids.clear();
        for (int i = 0; i < childrenBeanList.size(); i++) {
            titles.add(childrenBeanList.get(i).getName());
            ids.add(childrenBeanList.get(i).getId());
            TagFragment fragment = new TagFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",childrenBeanList.get(i).getId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        viewpager.setAdapter(new TagFragmentAdapter(getSupportFragmentManager(),fragments,titles));
        tab.setupWithViewPager(viewpager);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_knowledgedetail;
    }
}
