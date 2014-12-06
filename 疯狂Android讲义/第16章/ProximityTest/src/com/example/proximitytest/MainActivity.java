package com.example.proximitytest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// 定义广州天河的大致经度纬度
		double longitude = 113.39;
		double latitude = 23.13;
		// 定义半径 （5公里)
		float radius = 5000;
		Intent intent = new Intent(this, ProximityAlertReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);
		// 添加临近警告
		lm.addProximityAlert(latitude, longitude, radius, -1, pi);
	}
}