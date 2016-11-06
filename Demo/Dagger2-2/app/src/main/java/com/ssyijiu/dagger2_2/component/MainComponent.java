package com.ssyijiu.dagger2_2.component;

import com.ssyijiu.dagger2_2.MainActivity;
import com.ssyijiu.dagger2_2.modules.CModule;

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
    CommonComponent getCommonComponent();
}
