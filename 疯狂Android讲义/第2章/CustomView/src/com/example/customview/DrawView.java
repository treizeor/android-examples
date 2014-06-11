package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
	
	public float currentX = 40;
	public float currentY = 50;
	
	// 定义、并创建画笔
	Paint p = new Paint();

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		// 设置画笔颜色
		p.setColor(Color.RED);
		p.setStyle(Style.FILL);
		canvas.drawCircle(currentX, currentY, 30, p);
	}
	
	// 重写触碰事件处理方法
	public boolean onTouchEvent(MotionEvent event) {
		currentX = event.getX();
		currentY = event.getY();
		// 通知当前组件重绘自己
		invalidate();
		return true;
		
	}

}
