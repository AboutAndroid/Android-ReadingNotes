package com.ssyijiu.mvpdemo2.Presenter;

import com.ssyijiu.mvpdemo2.model.LoginModel;
import com.ssyijiu.mvpdemo2.view.LoginView;

/**
 * Created by ssyijiu on 2016/10/20.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginPresenter extends BasePresenter <LoginView,LoginModel>{

    public void login(final String username, final String password) {
        getModel().getInstance().login(username, password, new LoginModel.LoginListener() {
            @Override
            public void onSuccess() {
                getView().showSuccess();
            }

            @Override
            public void onFailed() {
                getView().showFailed();
            }


        });
    }
}
