package com.ssyijiu.retrofit.bean.autovalue;

import android.support.annotation.Nullable;

/**
 * Created by ssyijiu on 2016/11/25.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface FriendInfoModel {
    long uid();
    int starred();
    @Nullable
    String friend_remark();
}
