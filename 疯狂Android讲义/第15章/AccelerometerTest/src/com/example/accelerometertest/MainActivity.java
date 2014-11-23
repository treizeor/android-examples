package com.example.accelerometertest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends Activity implements SensorEventListener {

	// 定义系统的Sensor管理器
	SensorManager sensorManager;
	EditText show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (EditText) findViewById(R.id.editText1);
		// 获取系统的Sensor管理器服务
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 为系统的加速度传感器注册监听器
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// 取消注册
		sensorManager.unregisterListener(this);
	}

	// 实现SensorEventListener接口必须实现的方法
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		StringBuilder sb = new StringBuilder();
		sb.append("X 方向上的加速度: ");
		sb.append(values[0]);
		sb.append("\nY 方向上的加速度: ");
		sb.append(values[1]);
		sb.append("\nZ 方向上的加速度: ");
		sb.append(values[2]);
		show.setText(sb.toString());
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
}