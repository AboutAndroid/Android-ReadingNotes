package com.ssyijiu.mvpdemo2.model.bean;

/**
 * Created by ssyijiu on 2016/12/8.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class User {
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
