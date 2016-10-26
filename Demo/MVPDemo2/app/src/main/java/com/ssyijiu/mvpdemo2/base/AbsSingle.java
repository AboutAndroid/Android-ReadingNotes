package com.ssyijiu.mvpdemo2.base;

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

