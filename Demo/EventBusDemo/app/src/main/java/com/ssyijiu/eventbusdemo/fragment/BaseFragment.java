package com.ssyijiu.eventbusdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ssyijiu.eventbusdemo.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ssyijiu on 2016/8/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mContext;
    protected View mRootView;


    @Override
    public void onStart() {
        // 注册EventBus
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mRootView =  inflater.inflate(getLayoutResId(),container,false);
        initView();
        return mRootView;
    }

    @Override
    public void onStop() {
        // 销毁EventBus
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(MessageEvent event){

    }

    /**
     * 获取布局
     * @return
     */
    public abstract int getLayoutResId();


    protected abstract void initView();

}
