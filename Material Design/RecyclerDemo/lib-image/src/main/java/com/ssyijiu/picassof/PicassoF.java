package com.ssyijiu.picassof;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class PicassoF {

    private static boolean ONLY_WIFI = false;


    public static void init(Context context, boolean onlyWifi) {
        Fresco.initialize(context);
        ONLY_WIFI = onlyWifi;
    }

    public static boolean isOnlyWifi() {
        return ONLY_WIFI;
    }

    public static void setOnlyWifi(boolean onlyWifi) {
        ONLY_WIFI = onlyWifi;
    }
}
