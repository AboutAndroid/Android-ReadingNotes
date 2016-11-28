package com.ssyijiu.mvpdemo2.presenter;

import android.os.Handler;

import com.ssyijiu.mvpdemo2.base.BasePresenter;
import com.ssyijiu.mvpdemo2.model.LoginModel;
import com.ssyijiu.mvpdemo2.model.LoginModelImpl;
import com.ssyijiu.mvpdemo2.ui.LoginView;


/**
 * Created by ssyijiu on 2016/10/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginPresenter() {
        initData();
        // 在这里 initData 横竖屏切换时不会重复 initData
        // 不要在这里做 view 的相关操作，view 未 attach 空指针
        // 不要在这里 attachView 当 Activity#onDestroy，但进程未死亡时，view 不会再次 attach
    }

    private void initData() {
    }


    // 通过静态变量延长 presenter 的生命周期
    public static LoginPresenter getInstance() {
        return getInstance(LoginPresenter.class);
    }


    public void login(final String username, final String password) {

        if (isViewAttached()) {
            getView().showLoading();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                LoginModelImpl.getInstance().login(username, password, new LoginModel.LoginListener() {
                    @Override
                    public void onSuccess() {
                        if (isViewAttached()) {
                            getView().showSuccess();
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
        }, 3000);
    }



    @Override
    public void initView() {

        // 根据 initData 的数据来 initView

        if(isViewAttached()) {
            getView().showHello();
        }
    }
}
