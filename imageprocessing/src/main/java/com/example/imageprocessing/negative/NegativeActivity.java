package com.example.imageprocessing.negative;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.imageprocessing.R;

public class NegativeActivity extends AppCompatActivity {
    private ImageView imageView;
    private LinearLayout layout;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negative);
        imageView = (ImageView) findViewById(R.id.iv_negative);
        layout = (LinearLayout) findViewById(R.id.activity_gray);
        bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.dongyang);
        handleImageNegative(bmp);
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1);
        sa.setDuration(2000);
        LayoutAnimationController lac = new LayoutAnimationController(sa,0.5F);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        layout.setLayoutAnimation(lac);
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

            //底片效果
            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

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
