package com.ssyijiu.retrofit.retrofit2.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by ssyijiu on 2016/11/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class _PersistentCookieStore {

    private static final String LOG_TAG = "PersistentCookieStore";
    private static final String COOKIE_PREFS = "Cookies_Prefs";

    // 一个 url 对应一组 cookie
    private final Map<String, ConcurrentHashMap<String, Cookie>> urlCookieMaps;
    private final SharedPreferences cookiePrefs;


    public _PersistentCookieStore(Context context) {
        cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        urlCookieMaps = new HashMap<>();

        //将持久化的 cookies 缓存到内存中 即map cookies
        Map<String, ?> prefsMap = cookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
            for (String name : cookieNames) {
                String encodedCookie = cookiePrefs.getString(name, null);
                if (encodedCookie != null) {
                    Cookie decodedCookie = decodeCookie(encodedCookie);
                    if (decodedCookie != null) {
                        if (!urlCookieMaps.containsKey(entry.getKey())) {
                            urlCookieMaps.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                        }
                        urlCookieMaps.get(entry.getKey()).put(name, decodedCookie);
                    }
                }
            }
        }
    }

    protected String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public void add(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);

        // cookie未过期，添加到 cookies
        if (!cookie.persistent()) {
            if (!urlCookieMaps.containsKey(url.host())) {
                urlCookieMaps.put(url.host(), new ConcurrentHashMap<String, Cookie>());
            }
            urlCookieMaps.get(url.host()).put(name, cookie);
        } else {

            // cookie 过期，从 cookies 中移除
            if (urlCookieMaps.containsKey(url.host())) {
                urlCookieMaps.get(url.host()).remove(name);
            }
        }

        //将 cookies 持久化到本地
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();

        ConcurrentHashMap<String, Cookie> cookieMap = urlCookieMaps.get(url.host());

        if (cookieMap != null) {
            Set<String> cookieSet = cookieMap.keySet();
            if (cookieSet != null) {
                String urlCookies = TextUtils.join(",", cookieSet);
                prefsWriter.putString(url.host(), urlCookies);
                prefsWriter.putString(name, encodeCookie(new _OkHttpCookies(cookie)));
                prefsWriter.apply();
            }
        }
    }

    public List<Cookie> get(HttpUrl url) {
        ArrayList<Cookie> ret = new ArrayList<>();
        if (urlCookieMaps.containsKey(url.host()))
            ret.addAll(urlCookieMaps.get(url.host()).values());
        return ret;
    }

    public boolean removeAll() {
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        prefsWriter.clear();
        prefsWriter.apply();
        urlCookieMaps.clear();
        return true;
    }

    public boolean remove(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);

        if (urlCookieMaps.containsKey(url.host()) && urlCookieMaps.get(url.host()).containsKey(name)) {
            urlCookieMaps.get(url.host()).remove(name);

            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
            if (cookiePrefs.contains(name)) {
                prefsWriter.remove(name);
            }
            prefsWriter.putString(url.host(), TextUtils.join(",", urlCookieMaps.get(url.host()).keySet()));
            prefsWriter.apply();

            return true;
        } else {
            return false;
        }
    }

    public List<Cookie> getCookies() {
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : urlCookieMaps.keySet())
            ret.addAll(urlCookieMaps.get(key).values());

        return ret;
    }

    /**
     * cookies 序列化成 string
     *
     * @param cookie 要序列化的cookie
     * @return 序列化之后的string
     */
    protected String encodeCookie(_OkHttpCookies cookie) {
        if (cookie == null)
            return null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(cookie);
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }

        return byteArrayToHexString(os.toByteArray());
    }

    /**
     * 将字符串反序列化成cookies
     *
     * @param cookieString cookies string
     * @return cookie object
     */
    protected Cookie decodeCookie(String cookieString) {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((_OkHttpCookies) objectInputStream.readObject()).getCookies();
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
        } catch (ClassNotFoundException e) {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e);
        }

        return cookie;
    }

    /**
     * 二进制数组转十六进制字符串
     *
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    protected String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /**
     * 十六进制字符串转二进制数组
     *
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    protected byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

}
