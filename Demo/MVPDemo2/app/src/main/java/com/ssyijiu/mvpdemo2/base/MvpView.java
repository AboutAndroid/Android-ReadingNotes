package com.ssyijiu.mvpdemo2.base;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface MvpView {

    void showError(String errorMsg);

    void showException(Throwable tr);

}
