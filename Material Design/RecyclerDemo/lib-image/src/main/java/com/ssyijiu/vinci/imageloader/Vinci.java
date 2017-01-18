package com.ssyijiu.vinci.imageloader;

import android.content.Context;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class Vinci {

    private static boolean ONLY_WIFI = false;


    public static void init(Context context, boolean onlyWifi) {
//        Fresco.initialize(context);
        ONLY_WIFI = onlyWifi;
    }

    public static boolean isOnlyWifi() {
        return ONLY_WIFI;
    }

    public static void setOnlyWifi(boolean onlyWifi) {
        ONLY_WIFI = onlyWifi;
    }

    private Vinci() {
    }


    private static class Lazy {

        // use Vinci load image
//        static final Loader INSTANCE = LoaderFactory.providePicassoLoader();

        // use Glide load image
        static final ILoader INSTANCE = LoaderFactory.provideGlideLoader();

        // use Fresco load image
//        static final Loader INSTANCE = LoaderFactory.provideFrescoLoader();

        // use Qiniu load image
//        static final Loader INSTANCE = LoaderFactory.provideQiniuLoader();
    }

    public static ILoader getInstance() {
        return Lazy.INSTANCE;
    }
}
