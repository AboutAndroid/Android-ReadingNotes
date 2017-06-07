package com.ssyijiu.easyupdate.callback;

/**
 * Created by ssyijiu on 2017/5/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface RequestCallback {

    void onSuccess(String result);
    void onFailure(String errorMsg);

}
