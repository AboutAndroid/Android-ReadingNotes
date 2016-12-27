package com.ssyijiu.fgp.switchlib;

import android.content.Context;

import com.squareup.picasso.Picasso;
import com.ssyijiu.common.utils.NetUtil;
import com.ssyijiu.fgp.R;
import com.ssyijiu.fgp.weight.MImageView;

/**
 * Created by ssyijiu on 2016/12/26.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

class PicassoLoader implements ImageLoader.Loader {

    private PicassoLoader() {
    }

    static final PicassoLoader INSTANCE = new PicassoLoader();

    @Override
    public void loadImage(Context context, String url, MImageView imageView) {
        Picasso.with(context)
                .load(url)
                .placeholder(R.color.red)
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
        Picasso.with(context)
                .load("http://")
                .placeholder(R.color.red)
                .into(imageView);
    }
}
