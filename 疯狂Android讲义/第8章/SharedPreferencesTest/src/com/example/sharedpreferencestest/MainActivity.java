package com.example.sharedpreferencestest;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取只能被本应用读写的SharedPreferences对象
		preferences = getSharedPreferences("test", MODE_PRIVATE);
		editor = preferences.edit();
		Button read = (Button) findViewById(R.id.read);
		Button write = (Button) findViewById(R.id.write);

		read.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 读取字符串数据
				String time = preferences.getString("time", null);
				// 读取int类型的数据
				int randNum = preferences.getInt("random", 0);
				String result = time == null ? "您暂时还未写入数据" : "写入时间为:" + time
						+ "\n上次生成的随机数为:" + randNum;
				Toast.makeText(MainActivity.this, result, 0).show();
			}
		});
		
		write.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日" + "hh:mm:ss");
				// 存入当前时间
				editor.putString("time", sdf.format(new Date()));
				// 存入一个随机数
				editor.putInt("random", (int)(Math.random() * 100));
				// 提交所有存入的数据
				editor.commit();
			}
		});

	}
}