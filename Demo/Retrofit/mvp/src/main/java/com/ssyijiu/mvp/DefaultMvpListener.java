package com.ssyijiu.mvp;

import com.ssyijiu.mvp.i.MvpListener;
import com.ssyijiu.mvp.i.MvpView;

/**
 * Created by ssyijiu on 2016/12/8.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class DefaultMvpListener<T> implements MvpListener<T> {

    private MvpView mView;

    public DefaultMvpListener(MvpView mvpView) {
        this.mView = mvpView;
    }

    @Override
    public void onError(String errorMsg) {
        if(mView != null) {
            mView.showError(errorMsg);
        }
    }

    @Override
    public void onException(Throwable tr) {
        if(mView != null) {
            mView.showException(tr);
        }
    }
}
