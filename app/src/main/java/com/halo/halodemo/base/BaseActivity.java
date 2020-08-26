package com.halo.halodemo.base;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.halo.halodemo.R;
import com.halo.halodemo.utils.ActivityCollector;
import com.halo.halodemo.utils.StatusBarHelper;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        StatusBarHelper.translucent(this);
        ActivityCollector.addActivity(this);
        View v = findViewById(R.id.toolbar);
        if(v != null){
            toolbar = (Toolbar)v;
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
            tv_title = toolbar.findViewById(R.id.title);
        }
        initView();
        initListener();
        initData();
    }

    public void setTitle(String title){
        tv_title.setText(title);
    }

    public void setNavigationOnClickListener(View.OnClickListener onClick){
        setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(onClick);
    }

    public void setNavigationIcon(int resId){
        toolbar.setNavigationIcon(resId);
    }

    public void displayBack(){
        setNavigationIcon(R.mipmap.back);
        setNavigationOnClickListener(v -> {
            View focusView = getWindow().getCurrentFocus();
            if (focusView != null) {
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(focusView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
            finish();
        });
    }

    /**
     * 加载布局
     * @return
     */
    protected abstract int initLayout();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化事件监听
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 保证同一按钮在1秒内只会响应一次点击事件
     */
    public abstract class OnSingleClickListener implements View.OnClickListener {
        //两次点击按钮之间的间隔，目前为1000ms
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onSingleClick(View view);

        @Override
        public void onClick(View view) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                lastClickTime = curClickTime;
                onSingleClick(view);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
