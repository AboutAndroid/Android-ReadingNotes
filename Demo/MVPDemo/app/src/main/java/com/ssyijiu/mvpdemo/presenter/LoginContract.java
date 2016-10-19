package com.ssyijiu.mvpdemo.presenter;



import com.ssyijiu.mvpdemo.base.IPresenter;
import com.ssyijiu.mvpdemo.base.IView;
import com.ssyijiu.mvpdemo.bean.UserBean;

/**
 * Created by lixiaoming on 2016/8/8.
 */
public interface LoginContract {
    /**
     * mvp的数据流向：view->presenter->model->presenter->view
     * presenter的作用有两个：
     * 1. 把数据交给model处理
     * 2. 根据数据处理结果刷新view
     */

    /**
     * 1. 把数据交给model处理
     */
    //定义了登录presenter的一些方法，IPresenter是所有Presenter的基类
    interface LoginPresenter extends IPresenter<LoginView> {
        void login(String name,String password);
    }

    /**
     * 2. 根据数据处理结果刷新view
     */
    //需要view层来实现的登录view接口，IView是所有view的基类
    interface LoginView extends IView {

        void showLoginStatusSuccess(UserBean userInfo);
        void showLoginStatusFailed();
        void showLoginStatusLoading();

    }

}




