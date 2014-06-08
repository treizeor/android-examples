package com.example.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MySrv extends Service {

	@Override
	// 必须实现的方法
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("--->>>onCreate-->>");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("--->>>onStartCommand-->>");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("--->>>onDestroy-->>");
	}

}
