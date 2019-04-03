package com.example.zhizihua.wanandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by zhizihua on 2019/3/25.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList();
    private FragmentManager manager;
    public FragmentAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.manager = fm;
        this.fragments = fragments;
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
}
