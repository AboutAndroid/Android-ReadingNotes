package com.ssyijiu.retrofit.mvp.model;

/**
 * Created by ssyijiu on 2016/11/14.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface MsgModel {

    interface MsgListener {

        void onSuccess(String resp);

        void onFailure(Throwable t);
    }

    void getPostMsg(MsgListener listener);

    void getGetMsg(MsgListener listener);

    void getGoldPrice(MsgListener listener);

    void getFinancingList(MsgListener listener);
}
