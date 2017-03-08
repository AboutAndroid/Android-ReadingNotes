package com.ssyijiu.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ssyijiu on 2017/3/7.
 * Github : ssyijiu
 * Email  : lxmyijiu@163.com
 *
 * http://www.gcssloop.com/customview/Canvas_BasicGraphics
 */

public class CanvasView extends View {

    private Paint mPaint;

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
        mPaint = new Paint();

        // 蓝色
        mPaint.setColor(Color.BLUE);
        // 填充模式
        mPaint.setStyle(Paint.Style.FILL);
        // 10px 宽
        mPaint.setStrokeWidth(10f);
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1. 绘制点
        canvas.drawPoint(100,100,mPaint);
        canvas.drawPoints(new float[]{
            600,500,
            600,600,
            600,700
        },mPaint);

        // 2. 绘制直线 (200,200)->(300,300)
        canvas.drawLine(200,200,300,300,mPaint);
        canvas.drawLines(new float[]{
            100,200,200,200,  // (100,200)->(200,200)
            100,300,200,300   // (100,300)->(200,300)
        },mPaint);

        // 3. 绘制矩形 左上角(600,600) 右下角(700,700)
        // 如果提供的坐标不是左上角和右下角将绘制不出来
        canvas.drawRect(600,600,700,700,mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        Rect rect = new Rect(700,700,800,900);
        canvas.drawRect(rect,mPaint);

        // 4. 绘制圆角矩形
        RectF rectF = new RectF(400,400,500,700);
        canvas.drawRoundRect(rectF,50,500,mPaint);
        // rx ry 椭圆的两个半径，
        // 在rx为宽度的一半，ry为高度的一半时，刚好是一个椭圆
        // 当rx大于宽度的一半，ry大于高度的一半时，均按照一半来处理。

        // 5. 绘制椭圆，实际上是一个矩形的内切圆
        RectF oval = new RectF(100,700,300,1200);
        canvas.drawOval(oval,mPaint);

        // 6. 绘制圆形 圆形：(1000,1000) 半径：300
        canvas.drawCircle(1000,1000,80,mPaint);

        // 7. 绘制圆弧
        // 圆弧是椭圆的一部分，(0,90):从0度(水平向右为0度)开始，s顺时针转动90度，useCenter 是否使用中心，true 使用 false 不使用
        RectF arc = new RectF(600,0,800,500); // 背景矩形
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(arc,mPaint);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.BLUE);
        canvas.drawArc(arc,0,90,false,mPaint);


        RectF arc2 = new RectF(300,700,500,1200); // 背景矩形
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(arc2,mPaint);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(arc2,0,90,true,mPaint);

        // 8. Paint
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(40);     //为了实验效果明显，特地设置描边宽度非常大

        // 描边
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,1400,100,paint);

        // 填充
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(500,1400,100,paint);

        // 描边加填充
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(800, 1400, 100, paint);


    }
}
