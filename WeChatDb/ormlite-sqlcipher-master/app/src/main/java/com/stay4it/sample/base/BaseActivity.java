package com.stay4it.sample.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by ssyijiu on 2017/1/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 *
 * 所有 Activity 的基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    private SparseArray<View> mViews = new SparseArray<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
        initView();
        if(getIntent() != null) {
            parseIntent(getIntent());
        }
        initData();
    }


    protected abstract int getLayoutResId();

    protected abstract void initView();

    protected void parseIntent(Intent intent) {}

    protected void initData() {}


    /**
     * 使用泛型重写 findViewById 省去强转
     * @param viewId R.id.xxx
     * @return (T)View
     */
    public <T extends View> T findView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
