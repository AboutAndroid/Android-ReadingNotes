package com.ssyijiu.retrofit.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum  ParamsMap {

    INSTANCE;

    private HashMap<String,String> tokenMap;
    ParamsMap() {
        tokenMap = new HashMap<>();
    }


    public Map<String,String> getTokenMap() {
        tokenMap.clear();
        tokenMap.put(CommonParams.CHANNEL_ID,"23");
        return tokenMap;
    }
}
