package com.example.imageprocessing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by androie on 2016/9/27.
 */

public class MyTextView extends TextView {

    Paint mPaint1, mPaint2;
    Paint mPaint3;
    int mViewWidth;
    int mTranslate;
    LinearGradient mLinearGradient;
    Matrix mGradientMatrix;

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setColor(Color.YELLOW);
        mPaint3 = new Paint();
        mViewWidth = 0;
        mGradientMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 背景绘制
         */
//        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
//        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
//        canvas.save();
//        canvas.translate(10,10);
//        super.onDraw(canvas);
//        canvas.restore();
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0){
                mPaint3 = getPaint();
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,
                        new int[]{Color.BLUE,0xffffffff,Color.BLUE},null, Shader.TileMode.CLAMP);
                mPaint3.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }
}