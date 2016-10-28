package com.ssyijiu.dagger2.module;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ssyijiu on 2016/10/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */


/**
@Module注解表示这个类提供生成一些实例用于注入
 */

@Module
public class MainModule {

    /**
     * @Provides 注解表示这个方法是用来创建某个实例对象的，这里我们创建返回Gson对象
     * 方法名随便，一般用provideXXX结构
     * @return 返回注入对象
     */
    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
