package com.ssyijiu.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ssyijiu on 2016/10/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Singleton
@Component
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
