package com.example.mediaplaytest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

public class MyVisualizerView extends View {
	// bytes数组保存了波形抽样点点值
	public byte[] bytes;
	public float[] points;
	public Paint paint = new Paint();
	public Rect rect = new Rect();
	public byte type = 0;

	public MyVisualizerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		bytes = null;
		paint.setStrokeWidth(1f);
		paint.setAntiAlias(true);
		paint.setColor(Color.GREEN);
		paint.setStyle(Style.FILL);
	}

	public void updateVisualizer(byte[] ftt) {
		bytes = ftt;
		// 通知组件重绘自己
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 当用户触碰该组件时切换波形类型
		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return false;
		}
		type++;
		if (type > 3) {
			type = 0;
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (bytes == null) {
			return;
		}
		// 绘制白色背景
		canvas.drawColor(Color.WHITE);
		// 使用Rect对象记录该组件的宽高
		rect.set(0, 0, getWidth(), getHeight());
		switch (type) {
		case 0: // 绘制块状波形图
			for (int i = 0; i < bytes.length - 1; i++) {
				float left = getWidth() * i / (bytes.length - 1);
				// 根据波形计算该矩形的高度
				float top = rect.height() - (byte) (bytes[i + 1] + 128)
						* rect.height() / 128;
				float right = left + 1;
				float bottom = rect.height();
				canvas.drawRect(left, top, right, bottom, paint);
			}
			break;
		case 1: // 绘制柱状波形图(每隔18个抽样点绘制一个矩形）
			for (int i = 0; i < bytes.length - 1; i += 18) {
				float left = rect.width() * i / (bytes.length - 1);
				float top = rect.height() - (byte) (bytes[i + 1] + 128)
						* rect.height() / 128;
				float right = left + 6;
				float bottom = rect.height();
				canvas.drawRect(left, top, right, bottom, paint);
			}
			break;

		case 2: // 绘制曲线波形
			// 如果point数组还未初始化
			if (points == null || points.length < bytes.length * 4) {
				points = new float[bytes.length * 4];
			}
			for (int i = 0; i < bytes.length - 1; i++) {
				// 计算第i个点的x坐标
				points[i * 4] = rect.width() * i / (bytes.length - 1);
				// 计算第i个点的y坐标
				points[i * 4 + 1] = (rect.height() / 2)
						+ ((byte) (bytes[i] + 128)) * 128 / (rect.height() / 2);
				// 计算第i+1个点的x坐标
				points[i * 4 + 2] = rect.width() * (i + 1) / (bytes.length - 1);
				// 计算第i+1个点的y坐标
				points[i * 4 + 3] = (rect.height() / 2)
						+ ((byte) (bytes[i + 1] + 128)) * 128
						/ (rect.height() / 2);
			}
			// 绘制波形曲线
			canvas.drawLines(points, paint);
			break;
		}
	}

}
