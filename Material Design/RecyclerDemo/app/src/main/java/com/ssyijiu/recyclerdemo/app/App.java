package com.ssyijiu.recyclerdemo.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ssyijiu on 2016/11/15.
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

        initLeakCanary();
    }

    public static Context getContext() {
        return sContext;
    }
    public static Resources getAppResources() {
        return sResources;
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
