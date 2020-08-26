package com.halo.halodemo.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.halo.halodemo.R;

public abstract class BaseFragment extends Fragment {
    protected FragmentActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), initLayout(), null);
        initView(inflate);
        initdata();
        return inflate;
    }

    protected abstract int initLayout();

    protected abstract void initView(View inflate);

    protected abstract void initdata();

    public boolean onBackPressed() {
        return false;
    }

    public boolean onBackPressed(boolean result) {
        return result;
    }
}
