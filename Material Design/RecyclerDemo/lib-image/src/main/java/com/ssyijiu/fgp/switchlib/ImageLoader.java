package com.ssyijiu.fgp.switchlib;

import android.content.Context;

import com.ssyijiu.fgp.weight.MImageView;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class ImageLoader {

    private ImageLoader() {
    }


    private static class Lazy {

        // use Picasso load image
//        static final Loader INSTANCE = LoaderFactory.providePicassoLoader();

        // use Glide load image
        static final Loader INSTANCE = LoaderFactory.provideGlideLoader();

        // use Fresco load image
//        static final Loader INSTANCE = LoaderFactory.provideFrescoLoader();

        // use Qiniu load image
//        static final Loader INSTANCE = LoaderFactory.provideQiniuLoader();
    }

    public static Loader getInstance() {
        return Lazy.INSTANCE;
    }

    public interface Loader {
        void loadImage(Context context, String url, MImageView imageView);
        void loadImageOnlyWifi(Context context, String url, MImageView imageView);
        void loadImageOnlyCache(Context context, String url, MImageView imageView);
    }
}
