package com.ssyijiu.fragmentdemo.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssyijiu.fragmentdemo.event.Event;
import com.ssyijiu.library.MLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by ssyijiu on 2016/12/15.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;  // 宿主 Activity

    protected boolean mIsFirstShow = true;  // 是否第一次显示，用于判断懒加载

    /** 记录 Fragment 的 show/hide 状态 */
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 恢复 Fragment show/hide 状态
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.hide(this);  // 将 Fragment 全部 hide，默认全部 show
            if (!isSupportHidden) {   // 将原来 show 的恢复
                ft.show(this);
            }
            ft.commit();
        }

        if (getArguments() != null) {
            parseArguments(getArguments());
        }

    }

    /**
     * @param inflater           布局填充器
     * @param container          fragment 的父布局，即来自 Activity 的布局
     * @param savedInstanceState fragment 恢复时保存的状态信息
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MLog.i("onCreateView");

        EventBus.getDefault().register(this);

        View mRootView = inflater.inflate(getFragmentResId(), container, false);

        initView(mRootView);

        return mRootView;
    }

    protected void parseArguments(Bundle arguments) {
    }

    protected abstract int getFragmentResId();

    protected abstract void initView(View rootView);


    @Override
    public void onSaveInstanceState(Bundle outState) {
        // 保存 Fragment 的 show/hide 状态
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden && mIsFirstShow) {
            initLazyData();
            mIsFirstShow = false;
        }
    }

    /**
     * 在这里做数据的懒加载
     */
    protected void initLazyData() {
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe
    public void onEvent(Event event) {
    }
}
