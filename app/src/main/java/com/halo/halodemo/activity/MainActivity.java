package com.halo.halodemo.activity;

import android.util.Log;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.halo.halodemo.R;
import com.halo.halodemo.adapter.MainPageAdapter;
import com.halo.halodemo.base.BaseActivity;
import com.halo.halodemo.base.BaseFragment;
import com.halo.halodemo.factory.FragmentFactory;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private BottomNavigationViewEx mBottomView;
    private ViewPager mViewPager;
    private MainPageAdapter adapter;
    private SparseIntArray items;
    private long firstTime;
    private String[] titleStr;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mBottomView = findViewById(R.id.bottomview);
        mViewPager = findViewById(R.id.viewpager);
        mBottomView.enableItemShiftingMode(true);
        mBottomView.enableAnimation(false);
    }

    @Override
    protected void initListener() {
        mBottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    previousPosition = position;
                    mViewPager.setCurrentItem(position);
                    setTitle(titleStr[position]);
                }
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        titleStr = new String[]{getResources().getString(R.string.music), getResources().getString(R.string.backup)
                , getResources().getString(R.string.friends)};
        int[] idStr = new int[]{R.id.i_music, R.id.i_backup, R.id.i_friends};
        items = new SparseIntArray(titleStr.length);
        setTitle(titleStr[0]);
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titleStr.length; i++) {
            items.put(idStr[i],i);
            fragments.add(FragmentFactory.createMainFragment(i));
        }
        adapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                firstTime = System.currentTimeMillis();
                ToastUtils.showLong("再按一次退出程序");
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}