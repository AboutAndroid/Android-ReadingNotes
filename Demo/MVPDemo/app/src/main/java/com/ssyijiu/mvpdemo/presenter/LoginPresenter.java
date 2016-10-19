package com.ssyijiu.mvpdemo.presenter;

import android.text.TextUtils;

import com.ssyijiu.mvpdemo.bean.UserBean;
import com.ssyijiu.mvpdemo.model.LoginModel;
import com.ssyijiu.mvpdemo.model.LoginModelImpl;

/**
 * Created by lixiaoming on 2016/8/8.
 */
public class LoginPresenter implements LoginContract.LoginPresenter {

    private LoginContract.LoginView mLoginView;
    private LoginModel mlLoginModel = LoginModelImpl.getInstance();

    @Override
    public void onCrate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void attach(LoginContract.LoginView loginView){
        mLoginView = loginView;
        mLoginView.initView();
    }

    public void login(final String username, final String password){
        //验证username，password的合法性,
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {

            mLoginView.showLoginStatusLoading();


            new Thread(new Runnable() {
                @Override
                public void run() {

                    mlLoginModel.login(username, password, new LoginModelImpl.LoginListener() {
                        @Override
                        public void onSuccessLogin(UserBean user) {
                            mLoginView.showLoginStatusSuccess(user);
                        }

                        @Override
                        public void onFailedLogin(int failed) {
                            mLoginView.showLoginStatusFailed();
                        }

                    });
                }
            }).start();
        } else {
            mLoginView.showLoginStatusFailed();
        }
    }
}
