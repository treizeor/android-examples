package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {

	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public float currentX = 40;
	public float currentY = 50;
	Paint paint = new Paint();
	
	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setColor(Color.RED);
		canvas.drawCircle(currentX, currentY, 15, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		this.currentX = event.getX();
		this.currentY = event.getY();
		this.invalidate();
		return true;
	}
}
