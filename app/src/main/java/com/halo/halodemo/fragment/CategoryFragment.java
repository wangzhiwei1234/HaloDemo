package com.halo.halodemo.fragment;

import android.view.View;
import android.widget.TextView;
import com.halo.halodemo.R;
import com.halo.halodemo.base.BaseFragment;

public class CategoryFragment extends BaseFragment {

    @Override
    protected int initLayout() {
        return R.layout.fragment1;
    }

    @Override
    protected void initView(View inflate) {
        TextView textView = inflate.findViewById(R.id.textview);
        textView.setText("11111");
    }

    @Override
    protected void initdata() {

    }

}
