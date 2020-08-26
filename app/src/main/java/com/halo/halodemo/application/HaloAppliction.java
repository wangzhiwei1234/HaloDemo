package com.halo.halodemo.application;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

public class HaloAppliction extends MultiDexApplication implements Thread.UncaughtExceptionHandler{
    public static HaloAppliction mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static HaloAppliction getContext() {
        return mContext;
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {

    }
}
