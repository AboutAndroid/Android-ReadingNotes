package com.ssyijiu.retrofit;

import android.app.Application;
import android.content.Context;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

/**
 * Created by ssyijiu on 2016/11/15.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class App extends Application {



    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);
    }

    public static Context getContext() {
        return mContext;
    }
}
