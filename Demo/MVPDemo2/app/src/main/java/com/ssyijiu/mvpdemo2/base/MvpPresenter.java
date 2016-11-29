package com.ssyijiu.mvpdemo2.base;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface MvpPresenter<V extends MvpView> {


    void attachView(V view);

    void init();

    void detachView();

}
