package com.example.locationtest;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends Activity {

	LocationManager locationManager;
	EditText show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (EditText) findViewById(R.id.editText1);
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		updateView(location);
		// 设置每3秒获取一次GPS的定位信息
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				3000, 8, new LocationListener() {

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub
						updateView(locationManager
								.getLastKnownLocation(provider));
					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub
						updateView(null);
					}

					@Override
					public void onLocationChanged(Location location) {
						// TODO Auto-generated method stub
						updateView(location);
					}
				});
	}

	private void updateView(Location location) {
		// TODO Auto-generated method stub
		if (location != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("实时位置信息:\n");
			sb.append("经度:");
			sb.append(location.getLongitude());
			sb.append("\n纬度:");
			sb.append(location.getLatitude());
			sb.append("\n高度:");
			sb.append(location.getAltitude());
			sb.append("\n速度:");
			sb.append(location.getSpeed());
			sb.append("\n方向:");
			sb.append(location.getBearing());
			show.setText(sb.toString());
		} else {
			show.setText("");
		}
	}
}