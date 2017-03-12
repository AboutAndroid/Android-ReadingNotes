package com.ssyijiu.canvas1test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by ssyijiu on 2017/3/8.
 * Github : ssyijiu
 * Email  : lxmyijiu@163.com
 */

public class CanvasView extends View {

    Paint mPaint = new Paint();

    public CanvasView(Context context) {
        super(context);
        init();
    }


    public CanvasView(Context context,
                      @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1. 画圆
        RectF rectF = new RectF(40,360,1040,1360);
        canvas.drawOval(rectF,mPaint);

        // 2. 画各种弧线
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF,270,200,true,mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF,250,20,true,mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF,210,40,true,mPaint);

    }
}
