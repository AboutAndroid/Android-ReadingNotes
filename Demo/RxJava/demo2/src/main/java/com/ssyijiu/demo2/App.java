package com.ssyijiu.demo2;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by ssyijiu on 2017/1/24.
 * Github : ssyijiu
 * Email  : lxmyijiu@163.com
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
