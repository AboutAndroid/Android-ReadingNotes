package com.ssyijiu.dagger2_2.component;

import com.ssyijiu.dagger2_2.Test;
import com.ssyijiu.dagger2_2.modules.CommonModule;

import dagger.Subcomponent;

/**
 * Created by ssyijiu on 2016/11/5.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Subcomponent(modules = CommonModule.class)
public interface CommonComponent {
    Test getTest();
}
