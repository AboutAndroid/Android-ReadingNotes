package com.ssyijiu.mvpdemo2;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ssyijiu.mvpdemo2.Presenter.IPresenter;
import com.ssyijiu.mvpdemo2.Presenter.LoginPresenter;
import com.ssyijiu.mvpdemo2.view.LoginView;

public class LoginActivity extends BaseActivity <LoginPresenter> implements LoginView{


    EditText et_username;
    EditText et_password;
    Button btn_login;
    TextView login_status;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        login_status = (TextView) findViewById(R.id.login_status);




        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if(getPresenter() != null) {
                    getPresenter().login(username,password);
                }

            }
        });



    }

    @Override
    public LoginPresenter onLoadPresenter() {
        return new LoginPresenter();
    }


    @Override
    public void showLoading() {
        login_status.setText("loading...");
    }

    @Override
    public void showSuccess() {
        login_status.setText("success");
    }

    @Override
    public void showFailed() {
        login_status.setText("failed");
    }
}
