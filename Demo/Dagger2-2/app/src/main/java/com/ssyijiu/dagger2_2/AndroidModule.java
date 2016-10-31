package com.ssyijiu.dagger2_2;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

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

    @Provides
    @Singleton
    Test provideTest() {
        return new Test(mApp);
    }
}
