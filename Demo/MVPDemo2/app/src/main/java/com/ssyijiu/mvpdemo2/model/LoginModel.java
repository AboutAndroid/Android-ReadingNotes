package com.ssyijiu.mvpdemo2.model;



/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginModel implements IModel {

    static LoginModel mLoginModel;

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


    public void login(String username, String password, LoginListener listener) {
        if (username.equals("lxm")) {
            listener.onSuccess();
        } else {
            listener.onFailed();
        }
    }

    public interface LoginListener {
        void onSuccess();
        void onFailed();
    }
}
