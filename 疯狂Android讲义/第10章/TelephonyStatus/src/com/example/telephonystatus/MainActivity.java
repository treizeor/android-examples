package com.example.telephonystatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	ListView showView;
	// 声明代表状态名单数组
	String[] statusNames;
	// 代表手机状态的集合
	ArrayList<String> statusValues = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取TelephonyManager对象
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		// 获取各种status名称的数组
		statusNames = getResources().getStringArray(R.array.statusNames);
		// 获取代表SIM卡状态的数组
		String[] simState = getResources().getStringArray(R.array.simState);
		// 获取代表电话类型的数组
		String[] phoneType = getResources().getStringArray(R.array.phoneType);
		// 获取设备编号
		statusValues.add(tm.getDeviceId());
		// 获取系统平台的版本
		statusValues.add(tm.getDeviceSoftwareVersion() != null ? tm
				.getDeviceSoftwareVersion() : "未知");
		// 获取网络运营商代号
		statusValues.add(tm.getNetworkOperator());
		// 获取网络运营商名称
		statusValues.add(tm.getNetworkOperatorName());
		// 获取手机网络类型
		statusValues.add(phoneType[tm.getPhoneType()]);
		// 获取设备所在位置
		statusValues.add(tm.getCellLocation() != null ? tm.getCellLocation()
				.toString() : "未知位置");
		// 获取SIM卡国别
		statusValues.add(tm.getSimCountryIso());
		// 获取SIM卡序列号
		statusValues.add(tm.getSimSerialNumber());
		// 获取SIM卡状态
		statusValues.add(simState[tm.getSimState()]);

		showView = (ListView) findViewById(R.id.listView1);
		ArrayList<Map<String, String>> status = new ArrayList<Map<String, String>>();

		for (int i = 0; i < statusValues.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", statusNames[i]);
			map.put("value", statusValues.get(i));
			status.add(map);
		}

		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, status,
				R.layout.line, new String[] { "name", "value" }, new int[] {
						R.id.name, R.id.value });
		showView.setAdapter(adapter);
	}
}