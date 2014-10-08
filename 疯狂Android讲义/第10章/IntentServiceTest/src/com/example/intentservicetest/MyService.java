package com.example.intentservicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		// 该方法内执行耗时任务可能导致ANR异常
		long endTime = System.currentTimeMillis() + 20 * 1000;
		System.out.println("---onStartCommand");
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		System.out.println("---耗时任务完成---");
		return START_STICKY;
	}

}
