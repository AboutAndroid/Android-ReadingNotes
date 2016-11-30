package com.ssyijiu.mvpdemo2.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ssyijiu on 2016/11/30.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class User implements Parcelable {
    public String username;
    public String password;

    protected User(Parcel in) {
        username = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
    }
}
