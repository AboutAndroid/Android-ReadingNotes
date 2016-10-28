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
        mPemo = "dagger2从入门到放弃";
    }

    public Poetry(String pemo) {
        this.mPemo = pemo;
    }

    public String getPemo() {
        return mPemo;
    }
}
