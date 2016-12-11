package com.ssyijiu.retrofit.bean.vo;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * Created by ssyijiu on 2016/11/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@AutoValue
public abstract class Financing implements Parcelable {
    public abstract String name();

    public static Financing create(String name) {

        // auto-value 默认生产带全部参数的构造函数
        // 如果参数为空，会抛出 NullPointerException
        return new AutoValue_Financing(name);
    }

    // auto-value 自动生成 equals、hashCode、toString
    // 如果你自己重写了，auto-value 不再生成
    @Override
    public String toString() {
        return name();
    }
}
