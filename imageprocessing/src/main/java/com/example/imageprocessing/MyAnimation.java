package com.example.imageprocessing;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

public class MyAnimation extends Animation{

    int mCenterWidth, mCenterHeight;

    public MyAnimation(){

    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
        setDuration(2000);
        setFillAfter(true);
        setInterpolator(new BounceInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        matrix.postScale(1,1-interpolatedTime,mCenterWidth,mCenterHeight);
    }
}
