package com.ssyijiu.picassof.switchlib;

import android.content.Context;

import com.ssyijiu.picassof.weight.MImageView;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class ImageLoader {

    private ImageLoader() {
    }


    private static class Lazy {

        // use PicassoF load image
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

        /** 总是加载图片 */
        void loadImageAlways(Context context, String url, MImageView imageView);

        /** 根据设置(是否仅WIFI加载图片)加载图片 */
        void loadImage(Context context, String url, MImageView imageView);

        /** 仅WIFI加载图片 */
        void loadImageOnlyWifi(Context context, String url, MImageView imageView);

        /** 只加载图片缓存 */
        void loadImageOnlyCache(Context context, String url, MImageView imageView);
    }
}
