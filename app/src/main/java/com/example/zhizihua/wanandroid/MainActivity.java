package com.example.zhizihua.wanandroid;

import android.view.KeyEvent;

import com.example.zhizihua.wanandroid.View.CustomViewPager;
import com.example.zhizihua.wanandroid.fragment.Fragment01;
import com.example.zhizihua.wanandroid.fragment.Fragment02;
import com.example.zhizihua.wanandroid.fragment.Fragment03;
import com.example.zhizihua.wanandroid.fragment.Fragment04;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab)
    PageNavigationView tab;
    @BindView(R.id.customViewPager)
    CustomViewPager customViewPager;
    private ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();
    private String TAG = "MainActivity";
    private long firstTime = 0;
    @Override
    protected void init() {
        Fragment01 fragment01 = new Fragment01();
        Fragment02 fragment02 = new Fragment02();
        Fragment03 fragment03 = new Fragment03();
        Fragment04 fragment04 = new Fragment04();
        fragments.add(fragment01);
        fragments.add(fragment02);
        fragments.add(fragment03);
        fragments.add(fragment04);
        customViewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        NavigationController navigationController = tab.material()
                .addItem(android.R.drawable.ic_menu_save, "首页")
                .addItem(android.R.drawable.ic_menu_compass, "知识体系")
                .addItem(android.R.drawable.ic_menu_search, "公众号")
                .addItem(android.R.drawable.ic_menu_help, "导航")
                .build();
        navigationController.setupWithViewPager(customViewPager);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {// 如果两次按键时间间隔大于800毫秒，则不退出
                ToastUtils.show("再按一次退出程序");
                firstTime = secondTime;// 更新firstTime
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
