package com.ssyijiu.retrofit.bean;

import com.google.auto.value.AutoValue;

/**
 * Created by ssyijiu on 2016/11/22.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 *
 * https://github.com/google/auto/blob/master/value/userguide/index.md
 * http://blog.piasy.com/2016/05/06/Perfect-Android-Model-Layer/
 */

@AutoValue
abstract class Animal {
    static Animal create(String name, int numberOfLegs) {
        // See "How do I...?" below for nested classes.
        return new AutoValue_Animal(name, numberOfLegs);
    }

    abstract String name();
    abstract int numberOfLegs();
}
