package com.ssyijiu.picassof.switchlib;

import android.content.Context;

import com.ssyijiu.picassof.weight.MImageView;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class QiniuLoader implements ImageLoader.Loader {

    private QiniuLoader() {
    }

    static final QiniuLoader INSTANCE = new QiniuLoader();

    @Override
    public void loadImageAlways(Context context, String url, MImageView imageView) {

    }

    @Override
    public void loadImage(Context context, String url, MImageView imageView) {

    }

    @Override
    public void loadImageOnlyWifi(Context context, String url, MImageView imageView) {

    }

    @Override
    public void loadImageOnlyCache(Context context, String url, MImageView imageView) {

    }
}
