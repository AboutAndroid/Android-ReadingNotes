package com.ssyijiu.picassof.imageloader;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class ImageClient {

    private ImageClient() {
    }


    private static class Lazy {

        // use PicassoF load image
//        static final Loader INSTANCE = LoaderFactory.providePicassoLoader();

        // use Glide load image
        static final ILoader INSTANCE = LoaderFactory.provideGlideLoader();

        // use Fresco load image
//        static final Loader INSTANCE = LoaderFactory.provideFrescoLoader();

        // use Qiniu load image
//        static final Loader INSTANCE = LoaderFactory.provideQiniuLoader();
    }

    public static ILoader getInstance() {
        return Lazy.INSTANCE;
    }
}
