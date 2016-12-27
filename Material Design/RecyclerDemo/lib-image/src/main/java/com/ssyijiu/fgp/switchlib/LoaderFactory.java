package com.ssyijiu.fgp.switchlib;

/**
 * Created by ssyijiu on 2016/12/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

@SuppressWarnings("all")
class LoaderFactory {

    static GlideLoader provideGlideLoader() {
        return GlideLoader.INSTANCE;
    }

    static PicassoLoader providePicassoLoader() {
        return PicassoLoader.INSTANCE;
    }

    static FrescoLoader provideFrescoLoader() {
        return  FrescoLoader.INSTANCE;
    }

    static QiniuLoader provideQiniuLoader() {
        return  QiniuLoader.INSTANCE;
    }
}
