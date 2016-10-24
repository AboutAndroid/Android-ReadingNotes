package com.ssyijiu.mvpdemo2.presenter;

import android.os.Handler;

import com.ssyijiu.mvpdemo2.model.LoginModel;

/**
 * Created by ssyijiu on 2016/10/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginPresenter extends LoginContract.Presenter {

    private LoginPresenter() {
        // 在这里 initData 横竖屏切换时不会重复 initData
        // 不要在这里做 view 的相关操作，view 未 attach 空指针
        // 不要在这里 attachView 当 Activity#onDestroy，但进程未死亡时，view 不会再次 attach
    }

    // 通过静态变量延长 presenter 的生命周期
    private static LoginPresenter instance = null;

    public static LoginPresenter getInstance() {
        if(instance == null) {
            synchronized (LoginPresenter.class) {
                instance = new LoginPresenter();
            }
        }
        return instance;
    }

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
        }, 3000);
    }

    @Override
    public void init() {
        getView().showHello();
    }

}
