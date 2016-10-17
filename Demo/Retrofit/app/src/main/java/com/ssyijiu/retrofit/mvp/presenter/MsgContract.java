package com.ssyijiu.retrofit.mvp.presenter;


import com.ssyijiu.retrofit.mvp.IPresenter;
import com.ssyijiu.retrofit.mvp.IView;

/**
 * Created by ssyijiu on 2016/10/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class MsgContract {

    public interface MsgPresenter extends IPresenter<MsgView>{
        void getMsg();
    }


    public interface MsgView extends IView {
        void showLoading(String loadingMsg);
        void showSuccess(String msg);
        void showFailed(String failedMsg);
    }


}
