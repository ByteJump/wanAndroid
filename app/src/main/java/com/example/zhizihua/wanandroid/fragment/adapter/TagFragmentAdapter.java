package com.example.zhizihua.wanandroid.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by zhizihua on 2019/3/28.
 */

public class TagFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList();
    private FragmentManager manager;
    private ArrayList<String> titles = new ArrayList<>();
    public TagFragmentAdapter(FragmentManager fm,ArrayList<Fragment> fragments,ArrayList<String> titles) {
        super(fm);
        this.manager = fm;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
