package com.example.getposttest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button get, post;
	TextView show;
	// 代表服务器响应的字符串
	String response;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				show.setText(response);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		get = (Button) findViewById(R.id.get);
		post = (Button) findViewById(R.id.post);
		show = (TextView) findViewById(R.id.show);
		get.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						response = GetPostUtil.sendGet(
								"http://192.168.3.102:9999", "a=1&b=2&v=ss");
						handler.sendEmptyMessage(0x123);
					}
				}.start();
			}
		});

		post.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						response = GetPostUtil.sendPost(
								"http://192.168.3.102:9999/",
								"name=treize&passwd=9999");
					}
				}.start();
				handler.sendEmptyMessage(0x123);
			}
		});
	}
}