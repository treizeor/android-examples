package com.example.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	static final int NOTIFICATION_ID = 0x123;
	NotificationManager nm;
	private int PRIVATE_ID = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取系统的NotificationManage服务
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);

		button1.setOnClickListener(new OnClickListener() {

			// 点击通知后启动的Activity，这里为演示启动了自身
			Intent intent = new Intent(MainActivity.this,
					MainActivity.class);
			PendingIntent pi = PendingIntent.getActivity(MainActivity.this,
					0, intent, 0);
			Notification notify = new Notification.Builder(
					MainActivity.this).setAutoCancel(true)
					.setTicker("您有新的消息")
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("一条新通知")
					.setContentText("恭喜你，您加薪了，工资增加1000%")
					.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
					.setWhen(System.currentTimeMillis())
					//.setContentIntent(pi)
					.build();
			@Override
			public void onClick(View v) {
				
				nm.notify(NOTIFICATION_ID, notify);
				nm.notify(PRIVATE_ID++, notify);
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nm.cancel(NOTIFICATION_ID);
			}
		});

	}
}