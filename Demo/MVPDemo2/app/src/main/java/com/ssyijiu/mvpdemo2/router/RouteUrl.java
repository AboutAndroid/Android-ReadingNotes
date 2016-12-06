package com.ssyijiu.mvpdemo2.router;

import com.ssyijiu.library.MLog;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ssyijiu on 2016/12/2.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class RouteUrl {

    public static String newUrl(String route, ParamMap map) {
        StringBuilder sbr = new StringBuilder();
        sbr.append(route).append("?");

        Map<String,String> paramMap = map.getParams();

        Set<String> keys = paramMap.keySet();
        for (String key : keys) {
            sbr.append(key).append("=").append(paramMap.get(key)).append("&");
        }
        sbr.deleteCharAt(sbr.length()-1);
        MLog.e(sbr);
        return sbr.toString();
    }

    public static class ParamMap {

        // A singleTop map
        private static Map<String,String> params = new HashMap<>();

        public ParamMap() {
            params.clear();
        }

        public synchronized ParamMap add(String key,String value) {
            params.put(key,value);
            return this;
        }

        private synchronized Map<String, String> getParams() {
            return params;
        }
    }
}
