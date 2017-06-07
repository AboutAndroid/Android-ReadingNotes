package com.ssyijiu.easyupdate.callback;

/**
 * Created by ssyijiu on 2017/5/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class DownFailureRun implements Runnable {

    private DownloadCallback callback;
    private String errorMsg;


    public DownFailureRun(DownloadCallback callback, String errorMsg) {
        this.callback = callback;
        this.errorMsg = errorMsg;
    }


    @Override public void run() {
        callback.downloadFailure(errorMsg);
    }
}