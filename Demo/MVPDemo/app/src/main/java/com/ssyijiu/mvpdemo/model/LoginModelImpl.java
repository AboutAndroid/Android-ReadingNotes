package com.ssyijiu.mvpdemo.model;

import com.ssyijiu.mvpdemo.bean.Failed;
import com.ssyijiu.mvpdemo.bean.UserBean;

/**
 * Created by lixiaoming on 2016/8/8.
 */
public class LoginModelImpl implements LoginModel{

    private static LoginModelImpl instance = null;
    public static LoginModelImpl getInstance() {
        if(instance == null) {
            synchronized (LoginModelImpl.class) {
                if(instance == null) {
                    instance = new LoginModelImpl();
                }
            }
        }

        return instance;
    }

    @Override
    public void login(String username,String password,final LoginListener loginListener){

        //1. username,password Model接收数据

        //2. Model处理数据
        if(username.equals("lxm") && password.equals("123")) {
            UserBean user = new UserBean(username,password);
            if(loginListener != null) {
                //3. 返回处理结果
                loginListener.onSuccessLogin(user);
            }

        } else {
            if(loginListener != null) {
                loginListener.onFailedLogin(Failed.SYSTEM_ERROR);
            }

        }
    }
}
