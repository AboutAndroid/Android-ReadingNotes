package com.ssyijiu.canvas1test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

/**
 * Created by ssyijiu on 2017/3/10.
 * Github : ssyijiu
 * Email  : lxmyijiu@163.com
 */

public class PieView extends View {

    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = { 0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000,
        0xFFFF8C69, 0xFF808080,
        0xFFE6B800, 0xFF7CFC00 };

    private int colorIndex = 0;

    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private List<PieData> mData;
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();


    public PieView(Context context) {
        super(context);
        initView();
    }


    public PieView(Context context,
                   @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
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

        if (mData == null || mData.size() == 0) {
            return;
        }

        float currentAngle = mStartAngle;

        // 将画布原点移动到 View 的中心
        canvas.translate(mWidth / 2, mHeight / 2);

        float r = (float) (Math.min(mWidth, mHeight) / 2.0 * 0.8);

        RectF rectF = new RectF(-r, -r, r, r);

        for (PieData pieData : mData) {
            if (colorIndex == mColors.length) {
                colorIndex = 0;
            }
            mPaint.setColor(mColors[colorIndex++]);

            canvas.drawArc(rectF, currentAngle, pieData.angle, true, mPaint);
            currentAngle += pieData.angle;
        }
    }


    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
        invalidate();  // 是整个 View 无效，之后会调用 onDraw 对 View 进行重绘
    }


    public void setData(List<PieData> pieDataList) {
        mData = pieDataList;
        initData();
        invalidate();
    }


    private void initData() {
        if (mData == null || mData.size() == 0) {
            return;
        }

        float sumValue = 0;
        for (PieData pieData : mData) {
            sumValue += pieData.value;
        }

        for (int i = 0; i < mData.size(); i++) {
            mData.get(i).angle = mData.get(i).value / sumValue * 360;
        }
    }
}
