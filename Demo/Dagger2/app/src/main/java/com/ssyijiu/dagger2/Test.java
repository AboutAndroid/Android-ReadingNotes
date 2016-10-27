package com.ssyijiu.dagger2;

import com.ssyijiu.library.MLog;

import javax.inject.Inject;

/**
 * Created by ssyijiu on 2016/10/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class Test {
    @Inject
    Test() {
        MLog.i("dagger2 test");
    }

    @Override
    public String toString() {
        return "this test for dagger2";
    }
}
