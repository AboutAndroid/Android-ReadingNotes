package com.ssyijiu.mvpdemo.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ssyijiu.mvpdemo.BaseActivity;
import com.ssyijiu.mvpdemo.R;
import com.ssyijiu.mvpdemo.bean.UserBean;
import com.ssyijiu.mvpdemo.base.IPresenter;
import com.ssyijiu.mvpdemo.presenter.LoginContract;
import com.ssyijiu.mvpdemo.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.login_status)
    TextView tvLoginStatus;
    private LoginPresenter mLoginPresenter = new LoginPresenter();
    private String username;
    private String password;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected IPresenter[] getPresenters() {
        return new IPresenter[]{mLoginPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mLoginPresenter.attach(this);
    }

    @Override
    public void showLoginStatusSuccess(UserBean userInfo) {
        tvLoginStatus.setText("success!");
    }

    @Override
    public void showLoginStatusFailed() {
        tvLoginStatus.setText("failed!");
    }

    @Override
    public void showLoginStatusLoading() {
        tvLoginStatus.setText("login...");
    }



    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        mLoginPresenter.login(username,password);
    }
}
