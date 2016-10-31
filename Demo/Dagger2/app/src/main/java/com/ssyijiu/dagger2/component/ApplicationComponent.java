package com.ssyijiu.dagger2.component;

import com.google.gson.Gson;
import com.ssyijiu.dagger2.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Gson getGson();// 暴露Gson对象接口

}
