package com.ssyijiu.mvpdemo2.presenter;

import com.ssyijiu.mvpdemo2.presenter.contract.UserInfoContract;

/**
 * Created by ssyijiu on 2016/11/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class UserInfoPresenter extends UserInfoContract.Presenter {


    public static UserInfoPresenter getInstance() {
        return getInstance(UserInfoPresenter.class);
    }

    @Override
    public void initView() {
        if(isViewAttached()) {
            getView().showLoading();
        }
    }

    @Override
    public void show() {
        if(isViewAttached()) {
            getView().showUserInfo();
        }
    }
}
