package com.ssyijiu.mvpdemo2.presenter;

import android.os.Handler;

import com.ssyijiu.mvpdemo2.base.DefaultMvpListener;
import com.ssyijiu.mvpdemo2.base.ModelManager;
import com.ssyijiu.mvpdemo2.model.LoginModel;
import com.ssyijiu.mvpdemo2.model.bean.User;
import com.ssyijiu.mvpdemo2.ui.LoginContract;


/**
 * Created by ssyijiu on 2016/10/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginPresenter extends LoginContract.Presenter {

    private LoginContract.Model mLoginModel
            = ModelManager.getModel(LoginModel.class);

    @Override
    public void init() {
        if (isViewAttached()) {
            getView().showHello();
        }
    }

    @Override
    public void login(final String username, final String password) {

        if (isViewAttached()) {
            getView().showLoading();
        }

        new Handler().postDelayed(() ->
                mLoginModel.login(username, password,
                        new DefaultMvpListener<User>(getView()) {
                            @Override
                            public void onSuccess(User bean) {
                                if (isViewAttached()) {
                                    getView().showUserInfo(bean);
                                }
                            }
                        }
                ), 1000);
    }
}
