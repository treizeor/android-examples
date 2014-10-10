package com.example.client;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aidlclient.R;
import com.example.service.ICat;
import com.example.service.ICat.Stub;

public class MainActivity extends Activity {

	private ICat catService;
	private Button get;
	EditText color, weight;

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			catService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// 获取远程Service的onBind方法返回的对象的代理
			catService = ICat.Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		get = (Button) findViewById(R.id.button1);
		color = (EditText) findViewById(R.id.editText1);
		weight = (EditText) findViewById(R.id.editText2);
		// 创建所需绑定的Service的Intent
		Intent intent = new Intent();
		intent.setAction("cn.treize.aidl.action.AIDL_SERVICE");
		// 绑定到远程Service
		bindService(intent, conn, Service.BIND_AUTO_CREATE);

		get.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					color.setText(catService.getColor());
					weight.setText(catService.getWeight() + "");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.unbindService(conn);
	}
}