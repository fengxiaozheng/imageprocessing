package com.example.imageprocessing.gl;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by androie on 2016/8/8.
 */
public class MyRender3D implements GLSurfaceView.Renderer {

    float[] taperVertices = new float[]{
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, -0.2f,
            0.5f, -0.5f, -0.2f,
            0.0f, -0.2f, 0.2f
    };

    int[] taperColors = new int[]{
            65535, 0, 0, 0,
            0, 65535, 0, 0,
            0, 0, 65535, 0,
            65535, 65535, 0, 0
    };

    private byte[] taperFacets = new byte[]{
            0, 1, 2,
            0, 1, 3,
            1, 2, 3,
            0, 2, 3
    };

    float[] cubeVertices = new float[]{
            0.5f, 0.5f, 0.5f,
            0.5f, -0.5f, 0.5f,
            -0.5f, -0.5f, 0.5f,
            -0.5f, 0.5f, 0.5f,
            0.5f, 0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f, 0.5f, -0.5f,
    };

    private byte[] cubeFacets = new byte[]{
            0, 1, 2,
            0, 2, 3,
            2, 3, 7,
            2, 6, 7,
            0, 3, 7,
            0, 4, 7,
            4, 5, 6,
            4, 6, 7,
            0, 1, 4,
            1, 4, 5,
            1, 2, 6,
            1, 5, 6
    };

    FloatBuffer taperVerticesBuffer;
    IntBuffer taperCololrBuffer;
    ByteBuffer taperFacetsBuffer;
    FloatBuffer cubeVerticesBuffer;
    ByteBuffer cubeFacetsBuffer;
    private float rotate;

    public MyRender3D(){
        taperVerticesBuffer = floatBufferutil(taperVertices);
        taperFacetsBuffer = ByteBuffer.wrap(taperFacets);
        taperCololrBuffer = intBufferUtil(taperColors);
        cubeVerticesBuffer = floatBufferutil(cubeVertices);
        cubeFacetsBuffer = ByteBuffer.wrap(cubeFacets);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,  GL10.GL_FASTEST);
        gl.glClearColor(0,0,0,0);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0,0,width,height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        float ratio = (float) width / height;
        gl.glFrustumf(-ratio,ratio,-1,1,1,10);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(-0.6f, 0.0f, -1.5f);
        gl.glRotatef(rotate, 0f, 0.2f, 0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, taperVerticesBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, taperCololrBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, taperFacetsBuffer.remaining(),
                GL10.GL_UNSIGNED_BYTE,taperFacetsBuffer);
        gl.glLoadIdentity();
        gl.glTranslatef(0.7f, 0.0f, -2.2f);
        gl.glRotatef(rotate, 0f, 0.2f, 0f);
        gl.glRotatef(rotate, 1f, 0f, 0f);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeVerticesBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, cubeFacetsBuffer.remaining(),
                GL10.GL_UNSIGNED_BYTE,cubeFacetsBuffer);
        gl.glFinish();
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        rotate += 1;
    }

    private IntBuffer intBufferUtil(int[] arr){
        IntBuffer mBuffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asIntBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

    private FloatBuffer floatBufferutil(float[] arr){
        FloatBuffer mBuffer;
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asFloatBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }
}
