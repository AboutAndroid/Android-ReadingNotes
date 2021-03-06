package com.ssyijiu.dagger2_2.modules;

import android.content.Context;
import android.location.LocationManager;

import com.ssyijiu.dagger2_2.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ssyijiu on 2016/10/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Module
public class AndroidModule {

    private final App mApp;

    public AndroidModule(App app) {
        mApp = app;
    }

    @Provides
    Context ApplicationContext() {
        return mApp;
    }

    @Provides
    LocationManager provideLocationManager() {
        return (LocationManager) mApp.getSystemService(Context.LOCATION_SERVICE);
    }

//    @Provides
//    Test provideTest() {
//        return new Test(mApp);
//    }
}
