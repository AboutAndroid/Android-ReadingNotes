//package com.ssyijiu.dagger2.module;
//
//import com.google.gson.Gson;
//
//import dagger.Module;
//import dagger.Provides;
//
///**
// * Created by ssyijiu on 2016/10/28.
// * Github: ssyijiu
// * E-mail: lxmyijiu@163.com
// */
//
//
///**
//一些第三方库中的类无法使用 @injecct 注入构造函数
// 采用 module + provides 的方法
// */
//
//@Module
//public class GsonModule {
//
//    @Provides
//    public Gson provideGson() {
//        return new Gson();
//    }
//}
