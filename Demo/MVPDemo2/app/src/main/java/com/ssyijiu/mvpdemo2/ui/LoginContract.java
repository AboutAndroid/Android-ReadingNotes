package com.ssyijiu.mvpdemo2.ui;

import com.ssyijiu.mvpdemo2.base.BasePresenter;
import com.ssyijiu.mvpdemo2.base.MvpListener;
import com.ssyijiu.mvpdemo2.base.MvpModel;
import com.ssyijiu.mvpdemo2.base.MvpView;
import com.ssyijiu.mvpdemo2.model.bean.User;

/**
 * Created by ssyijiu on 2016/11/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface LoginContract {

    interface View extends MvpView {

        void showLoading();

        void toUserInfo();

        void showError(String errorMsg);

        void showException(Throwable tr);

        void showHello();

        void showUserInfo(User user);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void login(final String username, final String password);
    }

    interface Model extends MvpModel {

        void login(String username, String password, MvpListener<User> listener);
    }
}
