package com.example.gesturetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends Activity implements OnGestureListener {
	// 定义手势检测器实例
	GestureDetector detector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		detector = new GestureDetector(this, this);
	}

	// 将该Activity上的触碰事件交给GestureDetector处理
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onDown", 0).show();
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		Toast.makeText(MainActivity.this, "onShowPress", 0).show();
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onSingleTapUp", 0).show();
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "onScroll", 0).show();
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		Toast.makeText(MainActivity.this, "onLongPress", 0).show();
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Toast.makeText(MainActivity.this, "onFling", 0).show();
		// TODO Auto-generated method stub
		return false;
	}
}