package com.halo.halodemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.halo.halodemo.base.BaseFragment;

import java.util.List;

public class MainPageAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> data;

    public MainPageAdapter(FragmentManager fm, List<BaseFragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }
}
