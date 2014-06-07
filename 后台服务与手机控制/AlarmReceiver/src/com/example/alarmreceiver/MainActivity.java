package com.example.alarmreceiver;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button button1, button2;
	private PendingIntent sender;
	private AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        
        // 获取AlarmManager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        
        button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 设置10秒后触发闹铃
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				calendar.add(Calendar.SECOND, 10);
				alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
			}
		});
        
        button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alarmManager.cancel(sender);
			}
		});
    }
}
