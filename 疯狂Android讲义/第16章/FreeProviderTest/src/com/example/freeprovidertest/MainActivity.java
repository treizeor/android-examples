package com.example.freeprovidertest;

import java.util.List;

import android.app.Activity;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView providers;
	LocationManager lm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		providers = (ListView) findViewById(R.id.listView1);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		// 创建一个locationProvider的过滤条件
		Criteria cri = new Criteria();
		// 设置要求LocationProvider必须是免费的
		cri.setCostAllowed(false);
		// 设置要求提供高度信息
		cri.setAltitudeRequired(true);
		// 设置要求能提供方向信息
		cri.setBearingRequired(true);
		// 获取所有符合条件的LocationProvider的名称
		List<String> providerNames = lm.getProviders(cri, false);
		System.out.println(providerNames.size() + "----------");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, providerNames);
		providers.setAdapter(adapter);
	}
}