package com.ssyijiu.retrofit.retrofit2.converter;

/**
 * Created by ssyijiu on 2016/11/22.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */
public class YLApiError extends Error {
    public String request;
    public String errcode;
    public String errmsg;

    public boolean isApiError() {
        return true;
    }
}
