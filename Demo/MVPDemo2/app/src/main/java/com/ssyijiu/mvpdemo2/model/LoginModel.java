package com.ssyijiu.mvpdemo2.model;


import com.ssyijiu.mvpdemo2.presenter.LoginContract;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginModel implements LoginContract.Model {

    private static LoginModel mLoginModel;

    public static LoginModel getInstance() {
        synchronized (LoginModel.class) {
            if (mLoginModel == null) {
                synchronized (LoginModel.class) {
                    mLoginModel = new LoginModel();
                }

            }
        }
        return mLoginModel;
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
