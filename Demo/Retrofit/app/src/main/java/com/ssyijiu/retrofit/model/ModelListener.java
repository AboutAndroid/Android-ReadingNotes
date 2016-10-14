package com.ssyijiu.retrofit.model;


/**
 * Created by ssyijiu on 2016/10/14.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */
public interface ModelListener {
    void onSuccess(Object resp);
    void onFailure(Throwable t);
}
