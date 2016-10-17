package com.ssyijiu.retrofit.mvp.presenter;

import com.ssyijiu.retrofit.bean.MultiResp;
import com.ssyijiu.retrofit.mvp.model.MsgManager;

/**
 * Created by ssyijiu on 2016/10/17.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class MsgPresenter implements MsgContract.MsgPresenter {

    MsgContract.MsgView mMsgView;

    @Override
    public void getMsg() {

        mMsgView.showLoading("Msg loading...");

        new Thread(new Runnable() {

            @Override
            public void run() {

                MsgManager.getInstance().getMsg(new MsgManager.MsgListener() {
                    @Override
                    public void getMsgSuccess(MultiResp resp) {
                        mMsgView.showSuccess(resp.responseParams);
                    }

                    @Override
                    public void getMsgFailed(Throwable t) {
                        mMsgView.showFailed(t.getMessage());
                    }
                });
            }
        }).start();

    }

    @Override
    public void attach(MsgContract.MsgView view) {
        mMsgView = view;
        mMsgView.initView();
    }
}
