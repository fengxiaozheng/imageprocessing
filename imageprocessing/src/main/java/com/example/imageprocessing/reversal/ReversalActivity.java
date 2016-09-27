package com.example.imageprocessing.reversal;

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

public class ReversalActivity extends AppCompatActivity {
    private Bitmap bmp;
    private ImageView imageView;

    //反转
    float colorMatrix[] = {-1, 0, 0, 1, 1,
        0, -1, 0, 1, 1,
        0, 0, -1, 1, 1,
        0, 0, 0, 1, 0,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversal);
        imageView = (ImageView) findViewById(R.id.iv_reversal);
        bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.dongyang);
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
    }
}
