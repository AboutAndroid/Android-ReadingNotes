package com.ssyijiu.mvpdemo2.Presenter;

import com.ssyijiu.mvpdemo2.model.IModel;
import com.ssyijiu.mvpdemo2.view.IView;



/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class BasePresenter <T extends IView,M extends IModel> implements IPresenter<T> {

    protected T mView;
    protected M mModel;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void start() {

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

    public T getView() {
        return mView;
    }
}
