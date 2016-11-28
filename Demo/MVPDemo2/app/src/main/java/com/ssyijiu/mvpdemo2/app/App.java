package com.ssyijiu.mvpdemo2.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ssyijiu on 2016/10/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        leakCanaryInit();


    }

    private void leakCanaryInit() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not initView your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
