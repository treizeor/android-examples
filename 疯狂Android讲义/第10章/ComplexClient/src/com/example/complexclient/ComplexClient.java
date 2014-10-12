package com.example.complexclient;

import java.util.List;

import com.example.service.IPet;
import com.example.service.Person;
import com.example.service.Pet;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ComplexClient extends Activity {

	private IPet petService;
	private Button get;
	EditText personView;
	ListView showView;

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			petService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			petService = IPet.Stub.asInterface(service);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		personView = (EditText) findViewById(R.id.editText1);
		showView = (ListView) findViewById(R.id.listView1);
		get = (Button) findViewById(R.id.button1);
		Intent intent = new Intent();
		intent.setAction("cn.terize.aidl.action.COMPLEX_SERVICE");
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
		get.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					String personName = personView.getText().toString().trim();
					// 调用远程Service方法
					List<Pet> pets = petService.getPets(new Person(1,
							personName, personName));
					// 将程序返回的List包装成ArrayAdapter
					ArrayAdapter<Pet> adapter = new ArrayAdapter<Pet>(
							ComplexClient.this, android.R.layout.simple_list_item_1,
							pets);
					showView.setAdapter(adapter);
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
		// 解除绑定
		this.unbindService(conn);
	}
}