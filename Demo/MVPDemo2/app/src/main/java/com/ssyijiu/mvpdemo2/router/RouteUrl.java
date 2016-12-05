package com.ssyijiu.mvpdemo2.router;

import java.util.Locale;

/**
 * Created by ssyijiu on 2016/12/2.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class RouteUrl {

    public static String newUrl(String route, String... params) {
        StringBuilder sbr = new StringBuilder();
        sbr.append(route).append("?");
        for (String param : params) {
            sbr.append(param).append("=%s&");
        }
        sbr.deleteCharAt(sbr.length()-1);
        return sbr.toString();
    }

    public static String formatUrl(String routeUrl,Object... values) {
        return String.format(Locale.getDefault(),routeUrl,values);
    }
}
