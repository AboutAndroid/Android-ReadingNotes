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
        // 在这里 initData 横竖屏切换时不会重复 initData
        // 不要在这里做 view 的相关操作，view 未 attach 空指针
        // 不要在这里 attachView 当 Activity#onDestroy，但进程未死亡时，view 不会再次 attach
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
