package com.example.otheractivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends LauncherActivity {
	String[] names = { "设置程序参数", "查看星际兵种" };
	// 定义两个Activity对应的实现类
	Class<?>[] classes = { MainActivity.class, MainActivity.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1, names);
		setListAdapter(adapter);
	}
	
	@Override
	protected Intent intentForPosition(int position) {
		// TODO Auto-generated method stub
		return new Intent(MainActivity.this, classes[position]);
	}
}