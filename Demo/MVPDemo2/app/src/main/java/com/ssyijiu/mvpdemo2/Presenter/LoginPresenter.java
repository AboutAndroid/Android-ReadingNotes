package com.ssyijiu.mvpdemo2.presenter;

import android.os.Handler;

import com.ssyijiu.mvpdemo2.model.LoginModel;

/**
 * Created by ssyijiu on 2016/10/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginPresenter extends LoginContract.Presenter {
    @Override
    public void login(final String username, final String password) {

        if (isViewAttach()) {
            getView().showLoading();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getModel().getInstance().login(username, password, new LoginModel.LoginListener() {
                    @Override
                    public void onSuccess() {
                        if (isViewAttach()) {
                            getView().showSuccess();
                        }
                    }

                    @Override
                    public void onFailed() {
                        if (isViewAttach()) {
                            getView().showFailed();
                        }
                    }
                });
            }
        }, 0);
    }
}
