package com.example.imageprocessing.nostalgia;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.imageprocessing.R;

import butterknife.BindView;

public class NostalgiaActivity extends AppCompatActivity {
    private Bitmap bmp;
    private ImageView imageView, ori;
    private float mDensity;
    private int mHeight;

    //怀旧
    float colorMatrix[] = {0.393F, 0.769F, 0.189F, 0, 0,
            0.349F, 0.686F, 0.168F, 0, 0,
            0.272F, 0.534F, 0.131F, 0, 0,
                           0, 0, 0, 1, 0,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nostalgia);
        imageView = (ImageView) findViewById(R.id.iv_nostalgia);
        ori = (ImageView) findViewById(R.id.iv_nostalgia_ori);
        bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.dongyang);
        setImageMatrix();
        mDensity = getResources().getDisplayMetrics().density;
        mHeight = (int) (mDensity * 200 + 0.5);
        animateopen(imageView);
        ori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageView.getVisibility() == View.GONE){
                    animateopen(imageView);
                }else {
                    animateclose(imageView);
                }
            }
        });
    }

    private void setImageMatrix() {
        Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(colorMatrix);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bmp, 0, 0, paint);
        imageView.setImageBitmap(bitmap);
    }

    private void animateopen(View view){
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropUpdate(view,0,mHeight);
        animator.start();
    }

    private void animateclose(final View view){
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropUpdate(view,origHeight,0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    private ValueAnimator createDropUpdate(final View view, int start, int end){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start,end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams =view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return valueAnimator;
    }
}
