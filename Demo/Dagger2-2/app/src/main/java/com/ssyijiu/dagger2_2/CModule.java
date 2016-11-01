package com.ssyijiu.dagger2_2;

import android.location.LocationManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ssyijiu on 2016/11/1.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Module
public class CModule {

    @PerActivity
    @Provides
    Test provideTest(LocationManager manager) {
        return new Test(manager);
    }
}
