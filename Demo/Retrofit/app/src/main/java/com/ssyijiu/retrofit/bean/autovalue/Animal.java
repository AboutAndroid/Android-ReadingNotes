package com.ssyijiu.retrofit.bean.autovalue;

import com.google.auto.value.AutoValue;

/**
 * Created by ssyijiu on 2016/11/22.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 *
 * http://www.jianshu.com/p/0e2be3536a4e
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
