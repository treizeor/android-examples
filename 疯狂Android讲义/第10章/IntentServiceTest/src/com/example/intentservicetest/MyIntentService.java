package com.example.intentservicetest;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {

	public MyIntentService() {
		super("MyIntentService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// 该方法可以执行任何耗时任务，比如下载文件等，此处只是让线程等待20秒
		long endTime = System.currentTimeMillis() + 20 *1000;
		System.out.println("---onHandleIntent---");
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		System.out.println("---耗时任务执行完成---");
	}

}
