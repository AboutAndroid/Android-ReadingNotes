package com.ssyijiu.picassof.imageloader;

import android.content.Context;

import com.ssyijiu.picassof.weight.MImageView;

/**
 * Created by ssyijiu on 2017/1/9.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public interface ILoader {

    /** 总是加载图片 */
    void loadImageAlways(Context context, String url, MImageView imageView);

    /** 根据设置(是否仅WIFI加载图片)加载图片 */
    void loadImage(Context context, String url, MImageView imageView);

    /** 仅WIFI加载图片 */
    void loadImageOnlyWifi(Context context, String url, MImageView imageView);

    /** 只加载图片缓存 */
    void loadImageOnlyCache(Context context, String url, MImageView imageView);
}
