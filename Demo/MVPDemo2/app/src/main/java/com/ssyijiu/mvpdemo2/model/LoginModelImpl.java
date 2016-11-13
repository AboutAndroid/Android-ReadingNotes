package com.ssyijiu.mvpdemo2.model;


import com.ssyijiu.mvpdemo2.app.AbsSingle;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginModelImpl extends AbsSingle implements LoginModel {

    public static LoginModelImpl getInstance() {
        return getInstance(LoginModelImpl.class);
    }


    @Override
    public void login(String username, String password, LoginListener listener) {
        if (username.equals("lxm")) {
            listener.onSuccess();
        } else {
            listener.onFailed();
        }
    }

}
