package com.ssyijiu.dagger2_2;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ssyijiu on 2016/11/1.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
