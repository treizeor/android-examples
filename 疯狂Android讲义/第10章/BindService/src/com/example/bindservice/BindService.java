package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService extends Service {

	private int count;
	private boolean quit;

	private MyBinder binder = new MyBinder();

	// 通过继承Binder来实现IBinder类
	public class MyBinder extends Binder {
		public int getCount() {
			return count;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("---Service is Binded");
		return binder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("---Service is Created");
		// 启动一条线程，动态修改count状态值
		new Thread() {
			public void run() {
				while (!quit) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					count++;
				}
			};
		}.start();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("---Service is UnBinded");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.quit = true;
		System.out.println("---Service is Destroyed");
	}

}
