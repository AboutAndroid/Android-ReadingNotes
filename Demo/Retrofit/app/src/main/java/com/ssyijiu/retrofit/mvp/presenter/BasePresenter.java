package com.ssyijiu.retrofit.mvp.presenter;

import com.ssyijiu.retrofit.mvp.view.IView;

/**
 * Created by ssyijiu on 2016/11/14.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BasePresenter<V extends IView> implements IPresenter<V> {

    private V mView;


    @Override
    public void attachView(V view) {
        mView = view;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public void detachView() {
        if(isViewAttached()) {
            mView = null;
        }
    }

    public V getView() {
        return mView;
    }
}
