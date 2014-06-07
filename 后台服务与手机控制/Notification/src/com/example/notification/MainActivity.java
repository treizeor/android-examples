package com.example.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button button1, button2;
	private NotificationManager nm;
	static final int NOTIFICATION_ID = 0x123;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			// 创建一个启动其他Active的Intent
			Intent intent = new Intent(MainActivity.this, OtherActivity.class);
			PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
			Notification notify = new Notification.Builder(this)
				.setAutoCancel(true)
				.setTicker("您有新的消息")
				.setSmallIcon(R.drawable.ic_launcher)
				//.setLargeIcon(R.drawable.ic_launcher)
				
				.setContentTitle("一条新通知")
				.setContentText("努力码字吧~~~")
				// 设置默认铃声和默认LED灯
				.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS)
				.setWhen(System.currentTimeMillis())
				.setContentIntent(pi)
				.build();
				
			nm.notify(NOTIFICATION_ID, notify);
			break;
		case R.id.button2:
			nm.cancel(NOTIFICATION_ID);
			break;
		}

	}

}
