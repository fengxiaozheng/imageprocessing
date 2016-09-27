package com.example.imageprocessing.gray;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.imageprocessing.R;

public class GrayActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap bmp;

    //灰度处理
    float colorMatrix[] = {0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
                           0, 0, 0, 1, 0,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gray);
        imageView = (ImageView) findViewById(R.id.iv_gray);
        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.dongyang);
        setImageMatrix();
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

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView,"translationY",10f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView,"scaleX",1f,0f,1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView,"scaleY",1f,0f,1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(animator1,animator2,animator3);
        set.start();
    }
}
