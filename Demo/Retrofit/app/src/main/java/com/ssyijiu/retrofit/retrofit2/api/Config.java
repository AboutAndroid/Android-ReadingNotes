package com.ssyijiu.retrofit.retrofit2.api;

/**
 * Created by ssyijiu on 2016/11/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface Config {

    // 公共参数
    String CHANNEL_ID = "channelId";
    String CHANNEL_ID_VALUE = "23";

    String TOKEN = "channelId";  // token取不到，暂时用channelId模拟公共参数


    // Headers
    String HEADER_WITH_TOKEN = "Auth-Type:TOKEN";
    String HEADER_TOKEN = "TOKEN";
}
