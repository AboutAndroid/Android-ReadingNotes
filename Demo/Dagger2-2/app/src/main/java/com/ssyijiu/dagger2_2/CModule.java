package com.ssyijiu.dagger2_2;

import android.location.LocationManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ssyijiu on 2016/11/1.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Module
public class CModule {

//    @PerActivity
//    @Provides
//    Test provideTest(LocationManager manager) {
//        return new Test(manager);
//    }

    private int testAge;

    CModule(int age) {
        testAge = age;
    }

    @Named("boy")
    @Provides
    Test provideBoy() {
        return new Test(0);
    }

    @Named("girl")
    @Provides
    Test provideGirl() {
        return new Test(testAge);
    }

    @Provides
    int provideAge() {
        return testAge;
    }

}
