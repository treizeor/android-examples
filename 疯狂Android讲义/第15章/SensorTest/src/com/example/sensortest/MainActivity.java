package com.example.sensortest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	EditText etOrientation;
	EditText etMagnetic;
	EditText etTemperature;
	EditText etLight;
	EditText etPressure;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etOrientation = (EditText) findViewById(R.id.editText1);
		etMagnetic = (EditText) findViewById(R.id.editText2);
		etTemperature = (EditText) findViewById(R.id.editText3);
		etLight = (EditText) findViewById(R.id.editText4);
		etPressure = (EditText) findViewById(R.id.editText5);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 为各个传感器注册监听器
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_GAME);
		sensorManager
				.registerListener(this, sensorManager
						.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
						SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
				SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
				SensorManager.SENSOR_DELAY_GAME);
	}

	protected void onStop() {
		sensorManager.unregisterListener(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		// 获取触发event的传感器类型
		int sensorType = event.sensor.getType();
		StringBuilder sb = null;
		switch (sensorType) {
		case Sensor.TYPE_ORIENTATION:
			sb = new StringBuilder();
			sb.append("绕Z轴转过的角度: ");
			sb.append(values[0]);
			sb.append("\n绕X轴转过的角度: ");
			sb.append(values[1]);
			sb.append("\n绕Y轴转过的角度: ");
			sb.append(values[2]);
			etOrientation.setText(sb.toString());
			break;
		case Sensor.TYPE_MAGNETIC_FIELD:
			sb = new StringBuilder();
			sb.append("X 方向上的角度: ");
			sb.append(values[0]);
			sb.append("\nY 方向上的角度: ");
			sb.append(values[1]);
			sb.append("\nZ 方向上的角度: ");
			sb.append(values[2]);
			etMagnetic.setText(sb.toString());
			break;
		case Sensor.TYPE_AMBIENT_TEMPERATURE:
			sb = new StringBuilder();
			sb.append("当前温度为: ");
			sb.append(values[0]);
			etTemperature.setText(sb.toString());
			break;
		case Sensor.TYPE_LIGHT:
			sb = new StringBuilder();
			sb.append("当前光强度为: ");
			sb.append(values[0]);
			etLight.setText(sb.toString());
			break;
		case Sensor.TYPE_PRESSURE:
			sb = new StringBuilder();
			sb.append("当前压力为: ");
			sb.append(values[0]);
			etPressure.setText(sb.toString());
			break;
		}
	}

	@Override
	// 当传感器精度发生变化是调用该方法
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	};
}