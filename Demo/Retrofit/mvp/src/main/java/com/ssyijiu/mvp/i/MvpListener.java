package com.ssyijiu.mvp.i;

/**
 * Created by ssyijiu on 2016/12/8.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface MvpListener<T> {

    void onSuccess(T bean);

    void onError(String errorMsg);

    void onException(Throwable tr);
}
