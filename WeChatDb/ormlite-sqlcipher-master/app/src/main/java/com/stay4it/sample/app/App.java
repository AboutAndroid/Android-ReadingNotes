package com.stay4it.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;


/**
 * Created by ssyijiu on 2016/11/1.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class App extends Application {
    private static Context sContext;
    private static Resources sResources;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        sResources = getResources();
    }

    public static Context getAppContext() {
        return sContext;
    }

    public static Resources getAppResources() {
        return sResources;
    }
}
