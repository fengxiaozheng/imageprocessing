package com.example.imageprocessing.saturation;

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

public class SaturationActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap bmp;

    //高饱和
    float colorMatrix[] = {1.438F, -0.122F, -0.016F, 0, -0.03F,
            -0.062F, 1.378F, -0.016F, 0, 0.005F,
            -0.062F, -0.122F, 1.483F, 0, -0.002F,
            0, 0, 0, 1, 0,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturation);
        imageView = (ImageView) findViewById(R.id.iv_saturation);
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
