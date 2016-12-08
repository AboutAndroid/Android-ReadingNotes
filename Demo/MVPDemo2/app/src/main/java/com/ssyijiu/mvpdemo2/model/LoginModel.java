package com.ssyijiu.mvpdemo2.model;


import com.ssyijiu.mvpdemo2.app.AbsSingle;
import com.ssyijiu.mvpdemo2.base.MvpListener;
import com.ssyijiu.mvpdemo2.model.bean.User;
import com.ssyijiu.mvpdemo2.ui.LoginContract;

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
    public void login(String username, String password, MvpListener<User> listener) {
        if(username.equals("lxm") && password.equals("123")) {
            listener.onSuccess(new User("lxm","123"));
        } else if (username.equals("lxm")) {
            listener.onError("密码错误");
        } else if (password.equals("123")) {
            listener.onError("用户名错误");
        } else {
            listener.onException(new Exception("用户名和密码都错啦"));
        }
    }
}
