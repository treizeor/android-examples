package com.example.texture3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class MainActivity extends Activity implements OnGestureListener {

	// 定义旋转角度
	private float anglex = 0f;
	private float angley = 0f;
	static final float ROTATE_FACTOR = 60;
	// 定义手势检测器实例
	GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GLSurfaceView glView = new GLSurfaceView(this);
		MyRenderer renderer = new MyRenderer(this);
		glView.setRenderer(renderer);
		setContentView(glView);
		// 创建手势检测器
		detector = new GestureDetector(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 将Activity的触碰事件交给GestureDetector处理
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		velocityX = velocityX > 2000 ? 2000 : velocityX;
		velocityX = velocityX < -2000 ? -2000 : velocityX;
		velocityY = velocityY > 2000 ? 2000 : velocityY;
		velocityY = velocityY < -2000 ? -2000 : velocityY;
		// 根据横向上的速度计算Y轴旋转的角度
		angley += velocityX * ROTATE_FACTOR / 4000;
		// 根据纵向上的速度计算X轴旋转的角度
		anglex += velocityY * ROTATE_FACTOR / 4000;
		return true;
	}

	public class MyRenderer implements Renderer {

		// 立方体的顶点座标（一共是36个顶点，组成12个三角形）
		private float[] cubeVertices = { -0.6f, -0.6f, -0.6f, -0.6f, 0.6f,
				-0.6f, 0.6f, 0.6f, -0.6f, 0.6f, 0.6f, -0.6f, 0.6f, -0.6f,
				-0.6f, -0.6f, -0.6f, -0.6f, -0.6f, -0.6f, 0.6f, 0.6f, -0.6f,
				0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f, -0.6f, 0.6f, 0.6f,
				-0.6f, -0.6f, 0.6f, -0.6f, -0.6f, -0.6f, 0.6f, -0.6f, -0.6f,
				0.6f, -0.6f, 0.6f, 0.6f, -0.6f, 0.6f, -0.6f, -0.6f, 0.6f,
				-0.6f, -0.6f, -0.6f, 0.6f, -0.6f, -0.6f, 0.6f, 0.6f, -0.6f,
				0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f, -0.6f, 0.6f, 0.6f,
				-0.6f, -0.6f, 0.6f, 0.6f, -0.6f, -0.6f, 0.6f, -0.6f, -0.6f,
				0.6f, 0.6f, -0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
				-0.6f, -0.6f, 0.6f, -0.6f, -0.6f, -0.6f, -0.6f, -0.6f, -0.6f,
				0.6f, -0.6f, -0.6f, 0.6f, -0.6f, 0.6f, 0.6f, -0.6f, 0.6f,
				-0.6f, };
		// 定义立方体所需要的6个面（一共是12个三角形所需的顶点）
		private byte[] cubeFacets = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
				13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28,
				29, 30, 31, 32, 33, 34, 35, };
		// 定义纹理贴图的座标数据
		private float[] cubeTextures = { 1.0000f, 1.0000f, 1.0000f, 0.0000f,
				0.0000f, 0.0000f, 0.0000f, 0.0000f, 0.0000f, 1.0000f, 1.0000f,
				1.0000f, 0.0000f, 1.0000f, 1.0000f, 1.0000f, 1.0000f, 0.0000f,
				1.0000f, 0.0000f, 0.0000f, 0.0000f, 0.0000f, 1.0000f, 0.0000f,
				1.0000f, 1.0000f, 1.0000f, 1.0000f, 0.0000f, 1.0000f, 0.0000f,
				0.0000f, 0.0000f, 0.0000f, 1.0000f, 0.0000f, 1.0000f, 1.0000f,
				1.0000f, 1.0000f, 0.0000f, 1.0000f, 0.0000f, 0.0000f, 0.0000f,
				0.0000f, 1.0000f, 0.0000f, 1.0000f, 1.0000f, 1.0000f, 1.0000f,
				0.0000f, 1.0000f, 0.0000f, 0.0000f, 0.0000f, 0.0000f, 1.0000f,
				0.0000f, 1.0000f, 1.0000f, 1.0000f, 1.0000f, 0.0000f, 1.0000f,
				0.0000f, 0.0000f, 0.0000f, 0.0000f, 1.0000f };

		private Context context;
		private FloatBuffer cubeVerticesBuffer;
		private ByteBuffer cubeFacetsBuffer;
		private FloatBuffer cubeTextureBuffer;
		// 定义本程序所使用的纹理
		private int texture;

		public MyRenderer(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
			cubeVerticesBuffer = floatBufferUtil(cubeVertices);
			cubeFacetsBuffer = ByteBuffer.wrap(cubeFacets);
			cubeTextureBuffer = floatBufferUtil(cubeTextures);
		}

		private FloatBuffer floatBufferUtil(float[] arr) {
			// TODO Auto-generated method stub
			FloatBuffer mBuffer;
			ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
			qbb.order(ByteOrder.nativeOrder());
			mBuffer = qbb.asFloatBuffer();
			mBuffer.put(arr);
			mBuffer.position(0);
			return mBuffer;
		}

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// TODO Auto-generated method stub
			gl.glDisable(GL10.GL_DITHER);
			gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
			gl.glClearColor(0, 0, 0, 0);
			gl.glShadeModel(GL10.GL_SMOOTH);
			gl.glEnable(GL10.GL_DEPTH_TEST);
			gl.glDepthFunc(GL10.GL_LEQUAL);
			// 启用2D纹理贴图
			gl.glEnable(GL10.GL_TEXTURE_2D);
			// 装载纹理
			loadTextur(gl);

		}

		private void loadTextur(GL10 gl) {
			// TODO Auto-generated method stub
			Bitmap bitmap = null;
			try {
				bitmap = BitmapFactory.decodeResource(context.getResources(),
						R.drawable.sand);
				int[] textures = new int[1];
				// 指定生成第N个纹理 (第一个参数指定生成一个纹理)textures负责存储所有纹理的代号
				gl.glGenTextures(1, textures, 0);
				// 获取textures纹理数组中的第一个纹理
				texture = textures[0];
				// 通知OpenGL将texture纹理绑定到GL10.GL_TEXTURE_2D目标中
				gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
				// 设置纹理被缩小时的滤波方式
				gl.glTexParameterf(GL10.GL_TEXTURE_2D,
						GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
				// ..放大时..
				gl.glTexParameterf(GL10.GL_TEXTURE_2D,
						GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
				// 设置在纵向横向都是平铺纹理
				gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
						GL10.GL_REPEAT);
				gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
						GL10.GL_REPEAT);
				// 加载位图生成纹理
				GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				// 生成纹理之后，回收位图
				if (bitmap != null) {
					bitmap.recycle();
				}
			}
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO Auto-generated method stub
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			float ratio = (float)width/height;
			gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			// TODO Auto-generated method stub
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT| GL10.GL_DEPTH_BUFFER_BIT);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			// 启用贴图坐标数组数据
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			// 把绘图中心移入屏幕2个单位
			gl.glTranslatef(0f, 0f, -2.0f);
			// 旋转图形
			gl.glRotatef(angley, 0, 1, 0);
			gl.glRotatef(anglex, 1, 0, 0);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeVerticesBuffer);
			// 设置贴图坐标数据
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, cubeTextureBuffer);
			// 执行纹理贴图
			gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
			gl.glDrawElements(GL10.GL_TRIANGLES, cubeFacetsBuffer.remaining(), GL10.GL_UNSIGNED_BYTE, cubeFacetsBuffer);
			
			gl.glFinish();
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		}

	}
}