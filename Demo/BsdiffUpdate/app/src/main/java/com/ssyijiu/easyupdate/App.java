package com.ssyijiu.easyupdate;

import android.app.Application;

/**
 * Created by ssyijiu on 2017/5/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Updater.init(this);
    }
}
