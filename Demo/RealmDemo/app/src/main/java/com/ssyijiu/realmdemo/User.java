package com.ssyijiu.realmdemo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ssyijiu on 2016/9/30.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class User extends RealmObject {
    @PrimaryKey
    public String id;
    public String name;
    public int age;


    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
