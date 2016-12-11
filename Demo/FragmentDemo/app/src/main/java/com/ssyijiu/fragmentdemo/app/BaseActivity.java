package com.ssyijiu.fragmentdemo.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by ssyijiu on 2016/12/11.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity extends AppCompatActivity {


    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        mContext = this;

        initView();

        if (getIntent() != null) {
            parseIntent(getIntent());
        }

        initData();
    }

    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected void parseIntent(Intent intent) {
    }

    protected void initData() {
    }


    private SparseArray<View> mViews = new SparseArray<>();

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
