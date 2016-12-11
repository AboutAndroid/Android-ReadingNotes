package com.ssyijiu.mvp;


import com.ssyijiu.mvp.i.MvpPresenter;
import com.ssyijiu.mvp.i.MvpView;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

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
