package com.ssyijiu.retrofit.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.View;

import com.ssyijiu.mvp.MvpActivity;
import com.ssyijiu.retrofit.R;
import com.ssyijiu.retrofit.utils.ToastUtil;

/**
 * Created by ssyijiu on 2016/12/11.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseActivity extends MvpActivity {

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

    @Override
    public void showError(String errorMsg) {
        ToastUtil.show(errorMsg);
    }

    @Override
    public void showException(Throwable tr) {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.error_tip))
                .setMessage(tr.getMessage())
                .setPositiveButton(getResources().getString(R.string.make_sure), null)
                .show();
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
