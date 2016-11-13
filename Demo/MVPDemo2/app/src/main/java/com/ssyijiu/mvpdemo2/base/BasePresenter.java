package com.ssyijiu.mvpdemo2.base;


import com.ssyijiu.mvpdemo2.app.AbsSingle;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BasePresenter<V extends IView>extends AbsSingle implements IPresenter<V> {

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
