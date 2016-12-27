package com.ssyijiu.fgp;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class FGP {
    public static void init(Context context) {
        Fresco.initialize(context);
    }
}
