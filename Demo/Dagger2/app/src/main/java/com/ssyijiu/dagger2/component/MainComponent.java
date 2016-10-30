package com.ssyijiu.dagger2.component;

import com.ssyijiu.dagger2.MainActivity;
import com.ssyijiu.dagger2.OtherActivity;
import com.ssyijiu.dagger2.PoetryScope;
import com.ssyijiu.dagger2.app.App;
import com.ssyijiu.dagger2.module.PoetryModule;

import dagger.Component;

/**
 * Created by ssyijiu on 2016/10/28.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */



@PoetryScope
@Component(dependencies = ApplicationComponent.class,modules = {PoetryModule.class})
public abstract class MainComponent {


    public abstract void inject(MainActivity activity);
    public abstract void inject(OtherActivity activity);

    private static MainComponent instance;
    public static MainComponent getInstance() {
        if(instance == null) {
            instance = DaggerMainComponent.builder()
                    .applicationComponent(App.getInstance().getApplicationComponent())
                    .build();
        }

        return instance;
    }
}
