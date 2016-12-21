package com.ssyijiu.fragmentdemo.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;

import com.ssyijiu.fragmentdemo.event.Event;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

        initView(savedInstanceState);

        if (getIntent() != null) {
            parseIntent(getIntent());
        }

        initData();
    }


    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getLayoutResId();


    protected void parseIntent(Intent intent) {
    }

    protected void initData() {
    }


    @Override
    protected void onStart() {
        super.onStart();
        // EventBus 注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // EventBus 反注册
        EventBus.getDefault().unregister(this);
    }


    public void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment, int frameId, String tag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.commit();
    }

    private <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
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

    @Subscribe
    public void onEvent(Event event) {
    }
}
