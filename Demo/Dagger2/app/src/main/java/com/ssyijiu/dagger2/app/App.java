package com.ssyijiu.dagger2.app;

import android.app.Application;

import com.ssyijiu.dagger2.component.ApplicationComponent;
import com.ssyijiu.dagger2.component.DaggerApplicationComponent;

/**
 * Created by ssyijiu on 2016/10/30.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class App extends Application{
    private ApplicationComponent mApplicationComponent;

    private static App sApp;

    public static App getInstance() {
        return sApp;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;

        mApplicationComponent = DaggerApplicationComponent.builder().build();
    }
}
