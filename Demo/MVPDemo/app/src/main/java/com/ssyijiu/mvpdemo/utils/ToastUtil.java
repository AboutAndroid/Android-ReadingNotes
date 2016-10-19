package com.ssyijiu.mvpdemo.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast mToast;

	private ToastUtil(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("ToastUtil cannot be instantiated !");
	}

	private static Toast getInstance(Context context) {
		if(mToast == null) {
			mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		}
		return mToast;
	}

	public static void show(Context mContext, String msg) {
		mToast = getInstance(mContext);
		mToast.setText(msg);
		mToast.show();
	}
}
