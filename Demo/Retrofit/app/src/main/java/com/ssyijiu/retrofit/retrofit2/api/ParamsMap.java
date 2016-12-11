package com.ssyijiu.retrofit.retrofit2.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum ParamsMap {

    INSTANCE;

    private HashMap<String, String> tokenMap;

    ParamsMap() {
        tokenMap = new HashMap<>();
    }


    public Map<String, String> getTokenMap() {
        tokenMap.clear();

        // token 需要登录获取，暂时使用 CHANNEL_ID 模拟
        tokenMap.put(Config.CHANNEL_ID, "23");
        return tokenMap;
    }
}
