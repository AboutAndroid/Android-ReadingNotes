package com.ssyijiu.dagger2_2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ssyijiu on 2016/10/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Singleton
@Component(modules = AndroidModule.class)
public interface AppComponent {
    void inject(App app);
    void inject(MainActivity activity);
}
