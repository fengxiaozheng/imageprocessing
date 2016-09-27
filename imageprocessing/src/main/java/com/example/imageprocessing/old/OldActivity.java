package com.example.imageprocessing.old;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.imageprocessing.R;

public class OldActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old);
        imageView = (ImageView) findViewById(R.id.iv_old);
        bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.dongyang);
        handleImageNegative(bmp);
    }

    public Bitmap handleImageNegative(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color;
        int r, g, b, a;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        for (int i = 1; i < width * height; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            //老照片效果
            r = (int)(0.393 * r + 0.769 * g + 0.189 * b);
            g = (int)(0.349 * r + 0.686 * g + 0.168 * b);
            b = (int)(0.272 * r + 0.534 * g + 0.131 * b);

            if (r > 255){
                r = 255;
            } else if (r < 0){
                r = 0;
            }
            if (g > 255){
                g = 255;
            } else if (g < 0){
                g = 0;
            }
            if (b > 255){
                b = 255;
            } else if (b < 0){
                b = 0;
            }
            newPx[i] = Color.argb(a, r, g, b);
        }
        bitmap.setPixels(newPx, 0, width, 0, 0, width, height);
        imageView.setImageBitmap(bitmap);
        return bitmap;
    }
}
