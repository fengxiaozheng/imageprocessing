package com.example.imageprocessing.gl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.imageprocessing.R;

public class GlActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static float anglex = 0f;
    public static float angley = 0f;
    static final float ROTATE_FACTOR = 60;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        //    MyRenderer myRenderer = new MyRenderer();//平面图
         MyRender3D myRenderer = new MyRender3D();//3D图
    //    MyRenerer3DTexture myRenderer = new MyRenerer3DTexture(GlActivity.this);//3D纹理贴图
        glSurfaceView.setRenderer(myRenderer);
        setContentView(glSurfaceView);

        detector = new GestureDetector(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        velocityX = velocityX > 2000 ? 2000 : velocityX;
        velocityX = velocityX < -2000 ? -2000 : velocityX;
        velocityY = velocityY > 2000 ? 2000 : velocityY;
        velocityY = velocityY < -2000 ? -2000 : velocityY;
        angley += velocityX * ROTATE_FACTOR / 4000;
        anglex += velocityY * ROTATE_FACTOR / 4000;
        return true;
    }
}
