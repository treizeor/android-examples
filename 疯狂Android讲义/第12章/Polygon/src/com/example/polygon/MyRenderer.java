package com.example.polygon;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class MyRenderer implements Renderer {

	float[] triangleData = new float[] { .1f, .6f, .0f, // 上顶点
			-.3f, .0f, .0f, // 左顶点
			.3f, .1f, .0f // 右顶点
	};
	int[] triangleColor = new int[] { 65535, 0, 0, 0, // 上顶点红色
			0, 65535, 0, 0, // 左顶点绿色
			0, 0, 65535, 0 // 右顶点蓝色
	};
	float[] rectData = new float[] { .4f, .4f, .0f, // 右上顶点
			.4f, -.4f, .0f, // 右下顶点
			-.4f, .4f, .0f, // 左上顶点
			-.4f, -.4f, .0f // 左下顶点
	};
	int[] rectColor = new int[] { 0, 65535, 0, 0, // 右上顶点绿色
			0, 0, 65535, 0, // 右下顶点蓝色
			65535, 0, 0, 0, // 左上顶点红色
			65535, 65535, 0, 0 // 左下顶点黄色
	};
	float[] rectData2 = new float[] { -.4f, .4f, .0f, // 左上顶点
			.4f, .4f, .0f, // 右上顶点
			.4f, -.4f, .0f, // 右下顶点
			-.4f, -.4f, .0f // 左下顶点
	};
	float[] pentacle = new float[] { .4f, .4f, .0f, -.2f, .3f, .0f, .5f, .0f,
			.0f, -.4f, .0f, .0f, -.1f, -.3f, .0f };

	FloatBuffer triangleDataBuffer;
	IntBuffer triangleColorBuffer;
	FloatBuffer rectDataBuffer;
	IntBuffer rectColorBuffer;
	FloatBuffer rectDataBuffer2;
	FloatBuffer pentacleBuffer;
	// 控制旋转的角度
	private float rotate;

	public MyRenderer() {
		// 将顶点位置数据转换成FloatBuffer

		triangleDataBuffer = floatBufferUtil(triangleData);
		rectDataBuffer = floatBufferUtil(rectData);
		rectDataBuffer2 = floatBufferUtil(rectData2);
		pentacleBuffer = floatBufferUtil(pentacle);
		// 将顶点颜色数据数组转换成IntBuffer
		triangleColorBuffer = intBufferUtil(triangleColor);
		rectColorBuffer = intBufferUtil(rectColor);
	}

	// 定义一个工具方法，将int[]数组转换为OpenGL ES所需的IntBuffer
	private IntBuffer intBufferUtil(int[] arr) {
		// TODO Auto-generated method stub
		IntBuffer mBuffer;
		ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
		qbb.order(ByteOrder.nativeOrder());
		mBuffer = qbb.asIntBuffer();
		mBuffer.put(arr);
		mBuffer.position(0);
		return mBuffer;
	}

	// 定义一个工具方法，将float[]数组转换为OpenGL ES所需的FloatBuffer
	private FloatBuffer floatBufferUtil(float[] arr) {
		// TODO Auto-generated method stub
		FloatBuffer mBuffer;
		// 初始化ByteBuffer，长度为arr数组长度*4，因为一个int占4个字节
		ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
		// 数组排列用nativeOrder
		qbb.order(ByteOrder.nativeOrder());
		mBuffer = qbb.asFloatBuffer();
		mBuffer.put(arr);
		mBuffer.position(0);
		return mBuffer;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// 关闭抗抖动
		gl.glDisable(GL10.GL_DITHER);
		// 设置系统对透视进行修正
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0, 0, 0, 0);
		// 设置阴影平滑模式
		gl.glShadeModel(GL10.GL_SMOOTH);
		// 启用深度测试
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// 设置深度测试的类型
		gl.glDepthFunc(GL10.GL_LEQUAL);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// 设置3D视图的大小和位置
		gl.glViewport(0, 0, width, height);
		// 将当前矩阵模式设置为投影矩阵
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// 初始化单位矩阵
		gl.glLoadIdentity();
		// 计算透视视窗的宽度、高度比
		float ratio = (float) width / height;
		// 调用此方法设置透视窗的空间大小
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// 清除屏幕缓存和深度缓存
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// 启用顶点坐标数据
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// 启用顶点颜色数据
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		// 设置当前矩阵堆栈为模型堆栈
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// ---------------------绘制第一个2d图形-----------------------
		// 重置当前的模型视图矩阵
		gl.glLoadIdentity();
		gl.glTranslatef(-.32f, .35f, -1f);
		gl.glRotatef(rotate, 0f, 0f, 0.1f);
		// 设置顶点的位置数据
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, triangleDataBuffer);
		// 设置顶点的颜色数据
		gl.glColorPointer(4, GL10.GL_FIXED, 0, triangleColorBuffer);
		// 根据顶点数据绘制平面图形
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		// ---------------------绘制第二个2d图形-----------------------
		// 重置当前的模型视图矩阵
		gl.glLoadIdentity();
		gl.glTranslatef(.6f, .8f, -1.5f);
		gl.glRotatef(rotate, 0f, 0f, 0.1f);
		// 设置顶点的位置数据
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rectDataBuffer);
		// 设置顶点的颜色数据
		gl.glColorPointer(4, GL10.GL_FIXED, 0, rectDataBuffer);
		// 根据顶点数据绘制平面图形
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		// ---------------------绘制第三个2d图形-----------------------
		// 重置当前的模型视图矩阵
		gl.glLoadIdentity();
		gl.glTranslatef(-.4f, -.5f, -1.5f);
		gl.glRotatef(rotate, 0f, 0.2f, 0f);
		// 设置顶点的位置数据（依然使用之前的顶点颜色）
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rectDataBuffer2);
		// 根据顶点数据绘制平面图形
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		// ---------------------绘制第四个2d图形-----------------------
		// 重置当前的模型视图矩阵
		gl.glLoadIdentity();
		gl.glTranslatef(.4f, -.5f, -1.5f);
		// 设置使用纯色填充
		gl.glColor4f(1.0f, .2f, .2f, .0f);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		// 设置顶点的位置数据
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, pentacleBuffer);
		// 根据顶点数据绘制平面图形
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 5);
		// 绘制结束
		gl.glFinish();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		rotate += 1;

	}

}
