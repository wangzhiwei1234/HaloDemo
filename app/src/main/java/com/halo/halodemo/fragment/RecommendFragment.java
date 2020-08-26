package com.halo.halodemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.halo.halodemo.R;
import com.halo.halodemo.base.BaseFragment;

public class RecommendFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        return R.layout.fragment2;
    }

    @Override
    protected void initView(View inflate) {
        TextView textView = inflate.findViewById(R.id.textview);
        textView.setText("22222");
    }

    @Override
    protected void initdata() {

    }
}
