package com.example.gradienter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends Activity implements SensorEventListener {

	MyView show;
	// 定义水平仪能处理的最大倾斜角度，超过该角度，气泡将直接位于边界
	int MAX_ANGLE = 30;
	SensorManager sensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (MyView) findViewById(R.id.show);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		sensorManager.unregisterListener(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		int sensorType = event.sensor.getType();
		switch (sensorType) {
		case Sensor.TYPE_ORIENTATION:
			// 获取与Y轴的夹角
			float angleY = values[1];
			// 获取与Z轴夹角
			float angleZ = values[2];
			int baX = show.back.getWidth();
			int baY = show.back.getHeight();
			int buX = show.bubble.getWidth();
			int buY = show.bubble.getHeight();
			// 气泡位于中间时，气泡的X，Y坐标
			int x = (baX - buX) / 2;
			int y = (baY - buY) / 2;
			// 如果Z轴的倾斜角度在最大角度之内
			if (Math.abs(angleZ) <= MAX_ANGLE) {
				// 根据与Z轴的倾斜角度计算X坐标的变化值
				int deltaX = (int) ((baX - buX) / 2 * angleZ / MAX_ANGLE);
				x += deltaX;
			}
			// 如果与z轴的倾角大于MAX_ANGLE,气泡应跑到最左边
			else if (angleZ > MAX_ANGLE) {
				x = 0;
			}
			// 如果与z轴的倾角小于-MAX_ANGLE,气泡应跑到最左边
			else {
				x = baX - buX;
			}

			// 如果与Y轴的倾角还在最大角度内
			if (Math.abs(angleY) <= MAX_ANGLE) {
				int deltaY = (int) ((baY - buY) / 2 * angleY / MAX_ANGLE);
				y += deltaY;
			} else if (angleY > MAX_ANGLE) {
				y = baY - buY;
			} else {
				y = 0;
			}

			if (isContain(x, y)) {
				show.bubbleX = x;
				show.bubbleY = y;
			}
			show.postInvalidate();
			break;

		}
	}

	private boolean isContain(int x, int y) {
		// TODO Auto-generated method stub
		// 计算气泡的圆心坐标X，Y
		int bubbleCx = x + show.bubble.getWidth() / 2;
		int bubbleCy = y + show.bubble.getHeight() / 2;
		// 计算表盘的圆心坐标X\Y
		int backCx = show.back.getWidth() / 2;
		int backCy = show.back.getHeight() / 2;
		// 计算气泡的圆心与水平仪表盘的圆心之间的距离
		double distance = Math.sqrt((bubbleCx - backCx) * (bubbleCx - backCx)
				+ (bubbleCy - backCy) * (bubbleCy - backCy));
		// 若两个圆心的距离小于他们的半径差，即可认为处于仪表盘内
		if (distance < (show.back.getWidth() - show.bubble.getWidth()) / 2) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}