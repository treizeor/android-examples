package com.example.alarmchangewallpaper;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	AlarmManager aManager;
	Button start, stop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button)findViewById(R.id.start);
		stop = (Button)findViewById(R.id.stop);
		aManager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
		// 指定启动ChangeService服务
		Intent intent = new Intent(MainActivity.this, ChangeService.class);
		// 创建PendingIntent对象
		final PendingIntent pi = PendingIntent.getService(MainActivity.this, 0, intent, 0);
		
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 每隔5秒执行pi代表的组件一次
				aManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, 5000, pi);
				start.setEnabled(false);
				stop.setEnabled(true);
				Toast.makeText(MainActivity.this, "开启自动换壁纸", 0).show();
			}
		});
		
		stop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				start.setEnabled(true);
				stop.setEnabled(false);
				aManager.cancel(pi);
			}
		});
	}
}