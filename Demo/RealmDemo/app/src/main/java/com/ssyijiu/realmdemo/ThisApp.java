package com.ssyijiu.realmdemo;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by ssyijiu on 2016/9/30.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class ThisApp extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Realm.init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
