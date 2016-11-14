package com.ssyijiu.retrofit.mvp.view;

/**
 * Created by ssyijiu on 2016/11/14.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface MsgView extends IView {

    void showLoading(String loadingMsg);

    void showSuccess(Object resp);

    void showFailed(Throwable t);
}
