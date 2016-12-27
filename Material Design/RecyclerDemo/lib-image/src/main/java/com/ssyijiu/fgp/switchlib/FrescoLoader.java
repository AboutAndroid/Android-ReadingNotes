package com.ssyijiu.fgp.switchlib;

import android.content.Context;

import com.ssyijiu.common.utils.NetUtil;
import com.ssyijiu.fgp.weight.MImageView;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class FrescoLoader implements ImageLoader.Loader {

    private FrescoLoader() {
    }

    static final FrescoLoader INSTANCE = new FrescoLoader();

    @Override
    public void loadImage(Context context, String url, MImageView imageView) {
        imageView.setImageURI(url);
    }

    @Override
    public void loadImageOnlyWifi(Context context, String url, MImageView imageView) {
        if(NetUtil.isWIFI(context)) {
            loadImage(context,url,imageView);
        } else {
            loadImageOnlyCache(context,url,imageView);
        }
    }

    @Override
    public void loadImageOnlyCache(Context context, String url, MImageView imageView) {
        imageView.setImageURI("");
    }

}
