package com.ssyijiu.retrofit.bean.resp;

/**
 * Created by ssyijiu on 2016/11/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 *
 * 使用泛型封装网络返回基类
 * 服务器返回的 Resp 都继承这个类添加不同的泛型
 */

public class BaseResp<T> {

    /**d
     * rntCodeValue : 1
     * responseParams : http://openapi.gome.com.cn/oauth2/authorizeMobile?app_id=f420b4bf6c&redirect_uri=https://m.dbjb.com/login/codecallback&response_type=code&state=online
     * rntMsg :
     * errorMsg : 错误码:1,错误信息:
     * rntCode : OK
     */

    public int rntCodeValue;
    public T responseParams;
    public String rntMsg;
    public String errorMsg;
    public String rntCode;
}
