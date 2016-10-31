package com.ssyijiu.dagger2_1.component;

import com.ssyijiu.dagger2_1.MainActivity;
import com.ssyijiu.dagger2_1.module.AppModule;
import com.ssyijiu.dagger2_1.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ssyijiu on 2016/10/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */


@Singleton
@Component(modules = {AppModule.class,NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}
