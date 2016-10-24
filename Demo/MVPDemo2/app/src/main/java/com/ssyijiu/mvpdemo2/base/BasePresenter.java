package com.ssyijiu.mvpdemo2.base;


import android.os.Bundle;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V> {

    private V mView;
    private M mModel;


    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void onStart() {
    }


    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onSave(Bundle state) {

    }


    public boolean isViewAttach() {
        return mView != null;
    }

    public M getModel() {
        return mModel;
    }

    public V getView() {
        return mView;
    }

}
