package com.ssyijiu.fgp.switchlib;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ssyijiu.common.utils.NetUtil;
import com.ssyijiu.fgp.R;
import com.ssyijiu.fgp.weight.MImageView;

/**
 * Created by ssyijiu on 2016/12/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

class GlideLoader implements ImageLoader.Loader {

    private GlideLoader() {
    }

    static final GlideLoader INSTANCE = new GlideLoader();

    @Override
    public void loadImage(Context context, String url, MImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.color.green)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
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
        Glide.with(context)
                .load("")
                .placeholder(R.color.green)
                .into(imageView);
    }
}
