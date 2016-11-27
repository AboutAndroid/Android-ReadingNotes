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

    public int rntCodeValue;
    public T responseParams;  // 键不一样该怎么办？
    public String rntMsg;
    public String errorMsg;
    public String rntCode;
}
