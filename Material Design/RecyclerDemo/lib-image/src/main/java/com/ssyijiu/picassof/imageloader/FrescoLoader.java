package com.ssyijiu.picassof.imageloader;

import android.content.Context;

import com.ssyijiu.common.utils.NetUtil;
import com.ssyijiu.picassof.PicassoF;
import com.ssyijiu.picassof.weight.MImageView;

/**
 * Created by ssyijiu on 2016/12/27.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

class FrescoLoader implements ILoader {

    private FrescoLoader() {
    }

    static final FrescoLoader INSTANCE = new FrescoLoader();

    @Override
    public void loadImageAlways(Context context, String url, MImageView imageView) {
        imageView.setImageURI(url);
    }

    @Override
    public void loadImage(Context context, String url, MImageView imageView) {
        if(PicassoF.isOnlyWifi()) {
            loadImageOnlyWifi(context,url,imageView);
        } else {
            loadImageAlways(context,url,imageView);
        }
    }

    @Override
    public void loadImageOnlyWifi(Context context, String url, MImageView imageView) {
        if(NetUtil.isWIFI(context)) {
            loadImageAlways(context,url,imageView);
        } else {
            loadImageOnlyCache(context,url,imageView);
        }
    }

    @Override
    public void loadImageOnlyCache(Context context, String url, MImageView imageView) {
        imageView.setImageURI("");
    }

}
