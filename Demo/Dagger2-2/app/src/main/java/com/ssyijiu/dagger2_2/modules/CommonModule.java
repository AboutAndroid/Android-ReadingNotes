package com.ssyijiu.dagger2_2.modules;

import com.ssyijiu.dagger2_2.Test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ssyijiu on 2016/11/5.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Module
public class CommonModule {

    @Provides
    Test provideTest() {
        return new Test();
    }

}
