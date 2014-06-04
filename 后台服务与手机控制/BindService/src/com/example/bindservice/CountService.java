package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CountService extends Service {

	private boolean threadDisable;
	private int count;
	// 定义OnBind方法返回的对象
	private MyBinder myBinder = new MyBinder();
	
	
	// Service被创建时调用该方法
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v("CountService", "Service is onCreate");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while (!threadDisable) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
					Log.v("CountService", "Count is " + count);
				}
			}
		}).start();
	}
	
	// Service被关闭前调用该方法
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.threadDisable = true;
		Log.v("CountService", "Service is onDestroy");
	}
	
	// 必须实现的方法，绑定Service时回调改方法
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v("CountService", "Service is binded");
		// 返回 IBinder对象
		return myBinder;
	}
	
	// Service被断开时候回调该方法
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v("CountService", "Service is unbinded");
		return super.onUnbind(intent);
	}
	
	
	// 通过继承Binder类来实现IBinder类
	public class MyBinder extends Binder {
		public int getCount() {
			return count;
		}
	}

}
