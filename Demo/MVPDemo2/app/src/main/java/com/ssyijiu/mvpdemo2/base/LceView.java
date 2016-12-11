package com.ssyijiu.mvpdemo2.base;

/**
 * Created by ssyijiu on 2016/12/10.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface LceView extends MvpView {

    void showLoading();

    void showContent();

    void showEmpty();

    void showError();

}
