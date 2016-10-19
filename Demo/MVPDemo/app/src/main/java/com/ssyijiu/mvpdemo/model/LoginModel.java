package com.ssyijiu.mvpdemo.model;

import com.ssyijiu.mvpdemo.bean.Failed;
import com.ssyijiu.mvpdemo.bean.UserBean;

/**
 * Created by ssyijiu on 2016/10/19.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface LoginModel {
    void login(String username, String password, LoginListener loginListener);

    /**
     * 数据处理结果监听器
     */
    interface LoginListener {
        void onSuccessLogin(UserBean user);
        void onFailedLogin(int failed);
    }
}
