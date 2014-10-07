package com.example.bindservice;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	// 保持所启动的Service的IBinder对象
	BindService.MyBinder binder;
	// 定义一个ServiceConnection对象
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			System.out.println("---Service Disconnected");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("---Service Connected");
			// 获取Service的onBind方法所返回的MyBinder对象
			binder = (BindService.MyBinder) service;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bind = (Button) findViewById(R.id.bind);
		Button unbind = (Button) findViewById(R.id.unbind);
		Button getStatus = (Button) findViewById(R.id.getstatus);

		final Intent intent = new Intent();
		intent.setAction("cn.treize.service.BIND_SERVICE");

		bind.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 绑定指定Service
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});

		unbind.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 解除绑定Service
				unbindService(conn);
			}
		});

		getStatus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取并显示Service的count值
				Toast.makeText(MainActivity.this, "count:" + binder.getCount(),
						0).show();
			}
		});
	}
}