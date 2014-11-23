package com.example.gradienter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	// 定义水平仪表盘图片
	Bitmap back;
	// 定义水平仪表内部气泡图片
	Bitmap bubble;
	// 定义气泡的X，Y坐标
	int bubbleX, bubbleY;

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		back = BitmapFactory.decodeResource(getResources(), R.drawable.back);
		bubble = BitmapFactory
				.decodeResource(getResources(), R.drawable.bubble);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// 绘制表盘
		canvas.drawBitmap(back, 0, 0, null);
		// 根据气泡坐标绘制气泡
		canvas.drawBitmap(bubble, bubbleX, bubbleY, null);
	}
}
