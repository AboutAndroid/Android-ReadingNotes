package com.ssyijiu.mvpdemo2.model;


import com.ssyijiu.mvpdemo2.app.AbsSingle;
import com.ssyijiu.mvpdemo2.base.IModel;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by ssyijiu on 2016/11/12.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface LoginModel extends IModel{


    interface LoginListener {

        void onSuccess();

        void onFailed();
    }

    void login(String username, String password, LoginListener listener);
}
