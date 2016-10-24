package com.ssyijiu.mvpdemo2.presenter;

import com.ssyijiu.mvpdemo2.base.BasePresenter;
import com.ssyijiu.mvpdemo2.base.IModel;
import com.ssyijiu.mvpdemo2.base.IView;
import com.ssyijiu.mvpdemo2.model.LoginModel;

/**
 * Created by ssyijiu on 2016/10/23.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface LoginContract {

    interface Model extends IModel {
        void login(String username, String password, LoginListener listener);
        interface LoginListener {
            void onSuccess();
            void onFailed();
        }
    }

    interface View extends IView {

        void showLoading();

        void showSuccess();

        void showFailed();
    }

    abstract class Presenter extends BasePresenter<View, LoginModel> {
        public abstract void login(final String username, final String password);
    }

}
