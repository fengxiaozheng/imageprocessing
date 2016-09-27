package com.example.imageprocessing.gl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;

import com.example.imageprocessing.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by androie on 2016/8/8.
 */
public class MyRenerer3DTexture implements GLSurfaceView.Renderer {

    private float[] cubeVertices = {
            -0.6f, -0.6f, -0.6f,
            -0.6f, 0.6f, -0.6f,
            0.6f, 0.6f, -0.6f,
            0.6f, 0.6f, 0.6f,
            0.6f, -0.6f, -0.6f,
            -0.6f, -0.6f, -0.6f,
            -0.6f, -0.6f, 0.6f,
            0.6f, -0.6f, 0.6f,
            0.6f, 0.6f, 0.6f,
            0.6f, 0.6f, 0.6f,
            -0.6f, 0.6f, 0.6f,
            -0.6f, -0.6f, 0.6f,
            -0.6f, -0.6f, -0.6f,
            0.6f, -0.6f, -0.6f,
            0.6f, -0.6f, 0.6f,
            0.6f, -0.6f, 0.6f,
            -0.6f, -0.6f, 0.6f,
            -0.6f, -0.6f, -0.6f,
            0.6f, -0.6f, -0.6f,
            0.6f, 0.6f, -0.6f,
            0.6f, 0.6f, 0.6f,
            0.6f, 0.6f, 0.6f,
            0.6f, -0.6f, 0.6f,
            0.6f, -0.6f, -0.6f,
            0.6f, 0.6f, -0.6f,
            -0.6f, 0.6f, -0.6f,
            -0.6f, 0.6f, 0.6f,
            -0.6f, 0.6f, 0.6f,
            0.6f, 0.6f, 0.6f,
            0.6f, 0.6f, -0.6f,
            -0.6f, 0.6f, -0.6f,
            -0.6f, -0.6f, -0.6f,
            -0.6f, -0.6f, 0.6f,
            -0.6f, -0.6f, 0.6f,
            -0.6f, 0.6f, 0.6f,
            -0.6f, 0.6f, -0.6f,
    };

    private byte[] cubeFacets = {
            0, 1, 2,
            3, 4, 5,
            6, 7, 8,
            9, 10, 11,
            12, 13, 14,
            15, 16, 17,
            18, 19, 20,
            21, 22, 23,
            24, 25, 26,
            27, 28, 29,
            30, 31, 32,
            33, 34, 35,
    };

    private float[] cubeTextures = {
            1.0000f, 1.0000f, 1.0000f,
            0.0000f, 0.0000f, 0.0000f,
            0.0000f, 0.0000f, 0.0000f,
            1.0000f, 1.0000f, 1.0000f,
            0.0000f, 1.0000f, 1.0000f,
            1.0000f, 1.0000f, 0.0000f,
            1.0000f, 0.0000f, 0.0000f,
            0.0000f, 0.0000f, 1.0000f,
            0.0000f, 1.0000f, 1.0000f,
            1.0000f, 1.0000f, 0.0000f,
            1.0000f, 0.0000f, 0.0000f,
            0.0000f, 0.0000f, 1.0000f,
            0.0000f, 1.0000f, 1.0000f,
            1.0000f, 1.0000f, 0.0000f,
            1.0000f, 0.0000f, 0.0000f,
            0.0000f, 0.0000f, 1.0000f,
            0.0000f, 1.0000f, 1.0000f,
            1.0000f, 1.0000f, 0.0000f,
            1.0000f, 0.0000f, 0.0000f,
            0.0000f, 0.0000f, 1.0000f,
            0.0000f, 1.0000f, 1.0000f,
            1.0000f, 1.0000f, 0.0000f,
            1.0000f, 0.0000f, 0.0000f,
            0.0000f, 0.0000f, 1.0000f,
    };

    private FloatBuffer cubeVerticesBuffer;
    private ByteBuffer cubeFacetsBuffer;
    private FloatBuffer cubeTexturesBuffer;
    private int texture;
    private Context context;

    public MyRenerer3DTexture(Context context) {
        this.context = context;
        cubeVerticesBuffer = floatBufferutil(cubeVertices);
        cubeFacetsBuffer = ByteBuffer.wrap(cubeFacets);
        cubeTexturesBuffer = floatBufferutil(cubeTextures);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glClearColor(0, 0, 0, 0);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glEnable(GL10.GL_TEXTURE);
        loadTexture(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        float ratio = (float) width / height;
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0.0f, -2.0f);
        gl.glRotatef(GlActivity.angley, 0, 1, 0);
        gl.glRotatef(GlActivity.anglex, 1, 0, 0);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeVerticesBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, cubeTexturesBuffer);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
        gl.glDrawElements(GL10.GL_TRIANGLES, cubeFacetsBuffer.remaining(),
                GL10.GL_UNSIGNED_BYTE, cubeFacetsBuffer);
        gl.glFinish();
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    private void loadTexture(GL10 gl) {
        Bitmap bitmap = null;

        try {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.dongyang);
            int[] textures = new int[1];
            gl.glGenTextures(1, textures, 0);
            texture = textures[0];
            gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
            gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
            GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
    }

    private FloatBuffer floatBufferutil(float[] arr) {
        FloatBuffer mBuffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asFloatBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }
}
