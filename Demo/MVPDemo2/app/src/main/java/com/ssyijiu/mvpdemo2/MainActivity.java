package com.ssyijiu.mvpdemo2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzh.nonview.router.Router;
import com.ssyijiu.mvpdemo2.router.Routes;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        findViewById(R.id.button).setOnClickListener(v->{
//            Router.create(
//                    RouteUrl.formatUrl(
//                            RouteUrl.newUrl(Routes.LOGIN,Routes.LOGIN_USERNAME,Routes.LOGIN_PASSWORD),
//                            "lxm","123"
//                    )
//
//            ).open(MainActivity.this);


            Router.create(Routes.INFO+"?name=lxm").open(mContext);

        });
    }
}
