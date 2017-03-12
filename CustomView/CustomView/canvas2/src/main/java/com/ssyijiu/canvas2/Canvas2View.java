package com.ssyijiu.canvas2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ssyijiu on 2017/3/11.
 * Github : ssyijiu
 * Email  : lxmyijiu@163.com
 */

public class Canvas2View extends View {

    private Paint mPaint = new Paint();
    private int mWidth;
    private int mHeight;


    public Canvas2View(Context context) {
        super(context);
        init();
    }


    public Canvas2View(Context context,
                       @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // translate(canvas);
        // scale(canvas);
        // rotate(canvas);

        // rotate2(canvas);
        skew(canvas);

    }


    // X = x + sx * y
    // Y = y + sy * x
    private void skew(Canvas canvas) {

        mPaint.setStyle(Paint.Style.STROKE);

        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0,0,200,200);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect,mPaint);

        canvas.skew(1,0);                       // 水平错切 <- 45度

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect,mPaint);
    }


    private void rotate2(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        canvas.drawCircle(0, 0, 350, mPaint);
        canvas.drawCircle(0, 0, 400, mPaint);

        for (int i = 0; i < 12; i++) {
            canvas.drawLine(0, -350, 0, -400, mPaint);
            canvas.rotate(30);
        }
    }


    private void rotate(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0, -400, 400, 0);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint);

        // canvas.rotate(180);                     // 旋转180度 <-- 默认旋转中心为原点

        canvas.rotate(180, 200, 0);

        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint);
    }


    // scale(float sx, float sy, float px, float py)
    // x = (x + px) * sx
    // y = (y + py) * sy
    private void scale(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0, -400, 400, 0);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint);

        // canvas.scale(0.5f, 0.5f);       // 对画布进行缩放
        // canvas.scale(0.5f, 0.5f, 200, 0);
        // canvas.scale(0.5f, -0.5f);
        // canvas.scale(-0.5f, 0.5f, 200, 400);

        canvas.scale(0.5f, 0.5f);  // 缩放叠加
        canvas.scale(0.5f, 0.5f);

        mPaint.setColor(Color.RED);
        canvas.drawRect(rect, mPaint);  // 绘制红色矩形
    }


    public void translate(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        // 在坐标原点绘制一个蓝色圆形
        mPaint.setColor(Color.BLUE);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
    }
}
