package com.ssyijiu.recyclerdemo.utils;


import com.ssyijiu.recyclerdemo.app.App;

/**
 * Created by ssyijiu on 2016/8/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */
public class DensityUtil {

    private DensityUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("DensityUtil cannot be instantiated !");
    }

    private static float density = -1F;
    private static float scaledDensity = -1F;
    private static int widthPixels = -1;
    private static int heightPixels = -1;


    public static int dp2px(float dpValue) {
        return (int) (dpValue * getDensity() + 0.5F);
    }

    public static int px2dp(float pxValue) {
        return (int) (pxValue / getDensity() + 0.5F);
    }


    public static int sp2px(float spValue) {
        return (int) (spValue * getScaledDensity() + 0.5F);
    }

    public static int getScreenWidth() {
        if (widthPixels <= 0) {
            widthPixels = App.getAppResources().getDisplayMetrics().widthPixels;
        }
        return widthPixels;
    }


    public static int getScreenHeight() {
        if (heightPixels <= 0) {
            heightPixels = App.getAppResources().getDisplayMetrics().heightPixels;
        }
        return heightPixels;
    }

    private static float getDensity() {
        if (density <= 0F) {
            density = App.getAppResources().getDisplayMetrics().density;
        }
        return density;
    }

    private static float getScaledDensity() {
        if (scaledDensity <= 0F) {
            scaledDensity = App.getAppResources().getDisplayMetrics().scaledDensity;
        }
        return scaledDensity;
    }
}
