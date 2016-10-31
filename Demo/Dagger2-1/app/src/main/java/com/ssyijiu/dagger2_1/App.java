package com.ssyijiu.dagger2_1;

import android.app.Application;


import com.ssyijiu.dagger2_1.component.DaggerNetComponent;
import com.ssyijiu.dagger2_1.component.NetComponent;
import com.ssyijiu.dagger2_1.module.AppModule;
import com.ssyijiu.dagger2_1.module.NetModule;

/**
 * Created by ssyijiu on 2016/10/31.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class App extends Application {



    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
