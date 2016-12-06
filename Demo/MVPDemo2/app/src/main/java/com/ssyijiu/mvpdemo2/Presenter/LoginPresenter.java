package com.ssyijiu.mvpdemo2.presenter;

import android.os.Handler;

import com.ssyijiu.mvpdemo2.model.LoginModel;
import com.ssyijiu.mvpdemo2.presenter.contract.LoginContract;


/**
 * Created by ssyijiu on 2016/10/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginPresenter extends LoginContract.Presenter {

    public LoginPresenter() {
        initData();
    }

    private void initData() {
    }

    @Override
    public void login(final String username, final String password) {

        if (isViewAttached()) {
            getView().showLoading();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                LoginModel.getInstance().login(username, password, new LoginContract.Model.LoginListener() {
                    @Override
                    public void onSuccess() {
                        if (isViewAttached()) {
                            getView().toUserInfo();
                        }
                    }

                    @Override
                    public void onFailed() {
                        if (isViewAttached()) {
                            getView().showFailed();
                        }
                    }
                });
            }
        }, 1000);
    }

    @Override
    public void init() {

        // 根据 initData 的数据来 init

        if(isViewAttached()) {
            getView().showHello();
        }
    }
}
