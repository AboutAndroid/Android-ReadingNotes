package com.ssyijiu.mvpdemo2.ui;

import com.ssyijiu.mvpdemo2.base.IView;

/**
 * Created by ssyijiu on 2016/11/12.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface LoginView extends IView {

    void showLoading();

    void showSuccess();

    void showFailed();

    void showHello();
}
