package com.ssyijiu.mvpdemo2.app;

/**
 * Created by ssyijiu on 2016/10/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class AbsSingle {

    protected static <T extends AbsSingle> T getInstance(Class<T> clazz){
        return SingleManager.getInstance(clazz);
    }
}

