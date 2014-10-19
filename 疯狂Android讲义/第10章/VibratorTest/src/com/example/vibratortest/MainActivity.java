package com.example.vibratortest;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends Activity {

	Vibrator vibrator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Toast.makeText(MainActivity.this, "手机震动", Toast.LENGTH_SHORT).show();
		// 控制手机震动2秒
		vibrator.vibrate(2000);
		return super.onTouchEvent(event);
	}
}