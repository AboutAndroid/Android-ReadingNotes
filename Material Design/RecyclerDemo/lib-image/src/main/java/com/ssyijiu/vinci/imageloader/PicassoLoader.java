//package com.ssyijiu.picassof.imageloader;
//
//import android.content.Context;
//
//import com.squareup.picasso.Picasso;
//import com.ssyijiu.common.utils.NetUtil;
//import com.ssyijiu.picassof.Vinci;
//import com.ssyijiu.picassof.R;
//import com.ssyijiu.picassof.weight.ImageViewFresco;
//
///**
// * Created by ssyijiu on 2016/12/26.
// * Github: ssyijiu
// * E-mail: lxmyijiu@163.com
// */
//
//class PicassoLoader implements ILoader {
//
//    private PicassoLoader() {
//    }
//
//    static final PicassoLoader INSTANCE = new PicassoLoader();
//
//    @Override
//    public void loadImageAlways(Context context, String url, ImageViewFresco imageView) {
//        Picasso.with(context)
//                .load(url)
//                .placeholder(R.color.red)
//                .into(imageView);
//    }
//
//
//    @Override
//    public void loadImage(Context context, String url, ImageViewFresco imageView) {
//        if(Vinci.isOnlyWifi()) {
//            loadImageOnlyWifi(context,url,imageView);
//        } else {
//            loadImageAlways(context,url,imageView);
//        }
//    }
//
//    @Override
//    public void loadImageOnlyWifi(Context context, String url, ImageViewFresco imageView) {
//        if(NetUtil.isWIFI(context)) {
//            loadImageAlways(context,url,imageView);
//        } else {
//            loadImageOnlyCache(context,url,imageView);
//        }
//    }
//
//    @Override
//    public void loadImageOnlyCache(Context context, String url, ImageViewFresco imageView) {
//        Picasso.with(context)
//                .load("http://")
//                .placeholder(R.color.red)
//                .into(imageView);
//    }
//}
