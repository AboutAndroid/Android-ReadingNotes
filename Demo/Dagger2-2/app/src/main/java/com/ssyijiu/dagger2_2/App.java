package com.ssyijiu.dagger2_2;

import android.app.Application;
import android.location.LocationManager;

import com.ssyijiu.dagger2_2.component.AppComponent;
import com.ssyijiu.dagger2_2.component.DaggerAppComponent;
import com.ssyijiu.dagger2_2.modules.AndroidModule;
import com.ssyijiu.library.MLog;

import javax.inject.Inject;

/**
 * Created by ssyijiu on 2016/10/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class App extends Application {

    @Inject
    LocationManager locationManager;

    private AppComponent component;




    @Override
    public void onCreate() {
        super.onCreate();

        initMLog();

        component = DaggerAppComponent.builder().androidModule(new AndroidModule(this))
                .build();
        component.inject(this);

        MLog.i(locationManager);
    }



    public AppComponent getComponent() {
        return component;
    }

    private void initMLog() {
        MLog.TAG = "ssyijiu_dagger2";
        MLog.setLogLev(MLog.LogLev.E);
    }
}
