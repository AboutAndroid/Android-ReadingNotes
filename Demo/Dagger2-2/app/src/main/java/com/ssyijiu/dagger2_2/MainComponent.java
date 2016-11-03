package com.ssyijiu.dagger2_2;

import dagger.Component;

/**
 * Created by ssyijiu on 2016/11/1.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

//@PerActivity
@Component(/*dependencies = AppComponent.class,*/modules = CModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
