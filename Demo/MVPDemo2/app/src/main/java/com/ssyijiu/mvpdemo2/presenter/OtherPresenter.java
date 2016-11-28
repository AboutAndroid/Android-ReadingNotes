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

public class OtherPresenter extends BasePresenter<LoginView> {

    private OtherPresenter() {
        initData();
        // 在这里 initData 横竖屏切换时不会重复 initData
        // 不要在这里做 view 的相关操作，view 未 attach 空指针
        // 不要在这里 attachView 当 Activity#onDestroy，但进程未死亡时，view 不会再次 attach
    }

    private void initData() {
    }


    // 通过静态变量延长 presenter 的生命周期
    public static OtherPresenter getInstance() {
        return getInstance(OtherPresenter.class);
    }


    @Override
    public void initView() {

        // 根据 initData 的数据来 initView

        if(isViewAttached()) {
            getView().showHello();
        }
    }
}
