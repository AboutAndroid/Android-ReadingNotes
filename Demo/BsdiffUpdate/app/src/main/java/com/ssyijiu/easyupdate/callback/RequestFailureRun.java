package com.ssyijiu.easyupdate.callback;

/**
 * Created by ssyijiu on 2017/5/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class RequestFailureRun implements Runnable {

    private RequestCallback callback;
    private String errorMsg;


    public RequestFailureRun(RequestCallback callback, String errorMsg) {
        this.callback = callback;
        this.errorMsg = errorMsg;
    }


    @Override public void run() {
        callback.onFailure(errorMsg);
    }
}