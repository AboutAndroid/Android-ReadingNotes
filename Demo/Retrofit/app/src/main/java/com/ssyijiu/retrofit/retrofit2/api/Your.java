package com.ssyijiu.retrofit.retrofit2.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.ssyijiu.retrofit.App;

/**
 * Created by ssyijiu on 2016/11/25.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class Your {

    private static final String PRE_TOKEN = "pre_token";
    private static final String KEY_TOKEN = "key_token";

    public static volatile String sToken = "23"; // 默认为 null
    private static SharedPreferences sSP;

    private static SharedPreferences getInstance() {
        if (sSP == null) {
            sSP = App.getContext().getSharedPreferences(PRE_TOKEN, Context.MODE_PRIVATE);
        }
        return sSP;
    }


    public static void updateToken(String token) {
        // 1.更新内存 token
        sToken = token;
        // 2.更新缓存 token
        sSP = getInstance();
        sSP.edit().putString(KEY_TOKEN, token).apply();
    }

    public static String getToken() {
        sSP = getInstance();
        return sSP.getString(KEY_TOKEN, null); // 默认为 null
    }
}
