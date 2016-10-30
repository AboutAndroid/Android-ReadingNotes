package com.ssyijiu.dagger2.module;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ssyijiu on 2016/10/30.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    public Gson provideGson(){
        return new Gson();
    }
}
