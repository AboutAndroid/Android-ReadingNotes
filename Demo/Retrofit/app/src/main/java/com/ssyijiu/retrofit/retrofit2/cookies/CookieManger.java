package com.ssyijiu.retrofit.retrofit2.cookies;

import android.content.Context;

import com.ssyijiu.library.MLog;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by ssyijiu on 2016/11/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

// CookieManger 用来统一管理所有的 cookie
public class CookieManger implements CookieJar {

    private static Context mContext;

    private static PersistentCookieStore cookieStore;

    public CookieManger(Context context) {
        mContext = context;
        if (cookieStore == null) {
            cookieStore = new PersistentCookieStore(mContext);
        }

    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        MLog.i(cookies);

        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {

                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        MLog.e(cookies);
        return cookies;
    }
}