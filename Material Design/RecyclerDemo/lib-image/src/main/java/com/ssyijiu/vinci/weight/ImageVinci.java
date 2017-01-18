package com.ssyijiu.vinci.weight;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ssyijiu on 2016/12/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

public class ImageVinci extends ImageView {
    public ImageVinci(Context context) {
        super(context);
    }

    public ImageVinci(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageVinci(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public ImageVinci(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

// Fresco

//public class MImageView extends SimpleDraweeView {
//
//    public MImageView(Context context) {
//        super(context);
//    }
//
//    public MImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MImageView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    public MImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//
//    public MImageView(Context context, GenericDraweeHierarchy hierarchy) {
//        super(context, hierarchy);
//    }
//}
