package com.ssyijiu.mvpdemo2.base;


/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public abstract class BasePresenter<V extends IView, M extends IModel> extends AbsSingle implements IPresenter<V> {

    private V mView;
    private M mModel;


    @Override
    public void attachView(V view) {
        mView = view;
    }


    @Override
    public void detachView() {
        mView = null;
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
