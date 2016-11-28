package com.ssyijiu.mvpdemo2.ui;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.ssyijiu.mvpdemo2.R;
import com.ssyijiu.mvpdemo2.base.BaseActivity;
import com.ssyijiu.mvpdemo2.presenter.UserInfoPresenter;
import com.ssyijiu.mvpdemo2.presenter.contract.UserInfoContract;

/**
 * Created by ssyijiu on 2016/11/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class UserInfoActivity extends BaseActivity<UserInfoPresenter>
        implements UserInfoContract.View{


    TextView tvUserInfo;
    Button btnShow;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected UserInfoPresenter createPresenter() {
        return UserInfoPresenter.getInstance();
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void parseIntDataFromIntent(Intent intent) {

    }

    @Override
    public void showLoading() {
        tvUserInfo.setText(R.string.press_to_show);
    }

    @Override
    public void showUserInfo() {

    }
}
