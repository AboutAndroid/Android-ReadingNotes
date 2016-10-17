package com.ssyijiu.retrofit.mvp;

/**
 * Created by ssyijiu on 2016/10/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface IPresenter<V extends IView> {
    void attach(V view);
}
