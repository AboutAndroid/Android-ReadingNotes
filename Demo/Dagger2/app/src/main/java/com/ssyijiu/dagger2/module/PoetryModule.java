package com.ssyijiu.dagger2.module;

import com.ssyijiu.dagger2.PoetryScope;
import com.ssyijiu.dagger2.bean.Poetry;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ssyijiu on 2016/10/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */


@Module
public class PoetryModule {


    @PoetryScope
    @Provides
    public Poetry providePoetry(String pemo) {
        return new Poetry(pemo);
    }


    // 这里提供了一个生成String的方法，在这个Module里生成Poetry实例时，会查找到这里
    // 可以为上面提供String类型的参数
    @Provides
    public String providePemo() {
        return "清风吹不皱欲滴的清欢";
    }
}
