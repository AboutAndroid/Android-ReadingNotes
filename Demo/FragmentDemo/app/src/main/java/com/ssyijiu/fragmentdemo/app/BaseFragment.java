package com.ssyijiu.fragmentdemo.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssyijiu.library.MLog;

/**
 * Created by ssyijiu on 2016/12/15.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseFragment extends Fragment {
    Activity mActivity;
    View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.i("onCreate");

        // 初始化或恢复 Fragment 中组件的状态
    }

    /**
     *
     * @param inflater 布局填充器
     * @param container fragment 的父布局，即来自 Activity 的布局
     * @param savedInstanceState fragment 恢复时保存的状态信息
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MLog.i("onCreateView");

        mRootView = inflater.inflate(getFragmentResId(),container,false);
        return mRootView;
    }

    protected abstract int getFragmentResId();
}
