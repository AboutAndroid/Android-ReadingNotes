package com.ssyijiu.mvpdemo2.Presenter;

import com.ssyijiu.mvpdemo2.view.IView;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface IPresenter <T extends IView> {
    void attachView(T view);
    void start();
    void detachView();
}
