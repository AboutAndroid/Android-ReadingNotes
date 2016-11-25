package com.ssyijiu.retrofit.bean.autovalue;

import android.support.annotation.Nullable;

/**
 * Created by ssyijiu on 2016/11/25.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface CredentialInfo {
    @Nullable
    String phone();
    @Nullable
    String im_password();
    @Nullable
    String token();
}
