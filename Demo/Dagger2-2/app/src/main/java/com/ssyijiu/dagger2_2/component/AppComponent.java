package com.ssyijiu.dagger2_2.component;

import com.ssyijiu.dagger2_2.App;
import com.ssyijiu.dagger2_2.modules.AndroidModule;

import dagger.Component;

/**
 * Created by ssyijiu on 2016/10/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

//@Singleton
@Component(modules = {AndroidModule.class})
public interface AppComponent {
    void inject(App app);
//    void inject(MainActivity activity);

//    LocationManager getLocationManager();
}
