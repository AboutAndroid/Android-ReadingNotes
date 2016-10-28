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

     // 这个方法需要一个String参数，在Dagger2注入中，这些参数也是注入形式的，也就是
     // 要有其他对方提供参数poems的生成，不然会造成编译出错

    @PoetryScope
    @Provides
    public Poetry providePoetry(String pemo) {
        return new Poetry(pemo);
    }


    // 这里提供了一个生成String的方法，在这个Module里生成Poetry实例时，会查找到这里
    // 可以为上面提供String类型的参数
    @Provides
    public String providePemo() {
        return "从放弃到再次入门";
    }

}
