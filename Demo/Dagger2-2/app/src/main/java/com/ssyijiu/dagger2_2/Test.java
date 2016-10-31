package com.ssyijiu.dagger2_2;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ssyijiu on 2016/10/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */



//@Singleton
public class Test {

    public Context mContext;

    @Inject
    public Test(Context context) {

        // 注意：这个参数必须在某个 module中 provide

        mContext = context;
    }
}
