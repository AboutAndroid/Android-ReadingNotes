package com.ssyijiu.picassof.weight;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by ssyijiu on 2016/12/24.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */

//public class MImageView extends ImageView {
//    public MImageView(Context context) {
//        super(context);
//    }
//
//    public MImageView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MImageView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @TargetApi(21)
//    public MImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//}

// Fresco

public class MImageView extends SimpleDraweeView {

    public MImageView(Context context) {
        super(context);
    }

    public MImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }
}
