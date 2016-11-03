package com.ssyijiu.dagger2_2;

import android.content.Context;
import android.location.LocationManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ssyijiu on 2016/10/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */


public class Test {

//    public Context mContext;

//    @Inject
//    public Test(Context context) {
//        // 注意：这个参数必须在某个 module中 provide
//        mContext = context;
//    }



//    public LocationManager mLocationManager;
//
//    @Inject
//    public Test(LocationManager locationManager) {
//        this.mLocationManager = locationManager;
//    }



    public int mAge;

    @Inject
    public Test(int age) {
        this.mAge = age;
    }
}
