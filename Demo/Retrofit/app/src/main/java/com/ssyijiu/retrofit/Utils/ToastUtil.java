package com.ssyijiu.retrofit.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.ssyijiu.retrofit.app.App;


public class ToastUtil {
    private static Toast mToast;

    private ToastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("ToastUtil cannot be instantiated !");
    }

    private static Toast getInstance() {
        if (mToast == null) {
            mToast = Toast.makeText(App.getContext(), "", Toast.LENGTH_SHORT);
        }
        return mToast;
    }


    public static void show(int resId) {
        String msg = App.getAppResources().getString(resId);
        show(msg);
    }

    public static void show(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        mToast = getInstance();
        mToast.setText(msg);
        mToast.show();
    }

    public static void showCenter(int resId) {
        String msg = App.getAppResources().getString(resId);
        showCenter(msg);
    }

    public static void showCenter(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        mToast = getInstance();
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setText(msg);
        mToast.show();
    }
}
