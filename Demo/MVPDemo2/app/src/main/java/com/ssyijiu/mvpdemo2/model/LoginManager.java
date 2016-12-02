package com.ssyijiu.mvpdemo2.model;

/**
 * Created by ssyijiu on 2016/12/2.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public enum LoginManager {
    INSTANCE;
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
