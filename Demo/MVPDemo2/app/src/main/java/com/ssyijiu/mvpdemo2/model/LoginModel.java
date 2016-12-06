package com.ssyijiu.mvpdemo2.model;


import com.ssyijiu.mvpdemo2.app.AbsSingle;
import com.ssyijiu.mvpdemo2.presenter.contract.LoginContract;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginModel extends AbsSingle implements LoginContract.Model {

    private LoginModel() {
    }
    public static LoginModel getInstance() {
        return getInstance(LoginModel.class);
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
