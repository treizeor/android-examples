package com.example.readothersharedpreferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("WorldReadableFiles")
public class MainActivity extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Context useCount = null;

		try {
			// 获取其他程序的Context
			useCount = createPackageContext("com.example.usecount",
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 使用其他程序的context获取对应的SharedPreferences
		SharedPreferences preferences = useCount.getSharedPreferences("count",
				MODE_WORLD_READABLE);
		// 读取数据
		int count = preferences.getInt("count", 0);
		Toast.makeText(MainActivity.this, "UseCount应用被使用了:" + count + "次",
				Toast.LENGTH_LONG).show();
	}
}