package com.ssyijiu.dagger2.bean;

import javax.inject.Inject;

/**
 * Created by ssyijiu on 2016/10/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class Poetry {

    private String mPemo;


    @Inject
    public Poetry() {
        mPemo = "你是我眼睛里的海";
    }

    public Poetry(String pemo) {
        this.mPemo = pemo;
    }

    public String getPemo() {
        return mPemo;
    }
}
