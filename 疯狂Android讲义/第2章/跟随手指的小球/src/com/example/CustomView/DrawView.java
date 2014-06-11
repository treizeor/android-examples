package com.example.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
	public float currentX = 40;
	public float currentY = 50;
	
	// 定义并创建画笔
	Paint p = new Paint();
	public DrawView(Context context) {
		super(context);
	}
	public DrawView(Context context, AttributeSet set) {
		super(context, set);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		p.setColor(Color.RED);
		canvas.drawCircle(currentX, currentY, 15, p);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//return super.onTouchEvent(event);
		
		currentY = event.getY();
		invalidate();
		return true;
	}

}
