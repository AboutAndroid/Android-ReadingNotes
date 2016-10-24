package com.ssyijiu.mvpdemo2.model;


import com.ssyijiu.mvpdemo2.presenter.LoginContract;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginModel implements LoginContract.Model {

    private LoginModel() {

    }

    private static LoginModel instance;

    public static LoginModel getInstance() {
        synchronized (LoginModel.class) {
            if (instance == null) {
                synchronized (LoginModel.class) {
                    instance = new LoginModel();
                }

            }
        }
        return instance;
    }


    @Override
    public void login(String username, String password, LoginContract.Model.LoginListener listener) {
        if (username.equals("lxm")) {
            listener.onSuccess();
        } else {
            listener.onFailed();
        }
    }

}
