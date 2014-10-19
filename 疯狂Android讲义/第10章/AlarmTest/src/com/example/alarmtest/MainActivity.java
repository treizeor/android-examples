package com.example.alarmtest;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button setTime;
	AlarmManager alarmManager;

	// Calendar currentTime = Calendar.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTime = (Button) findViewById(R.id.setTime);
		alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
		setTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar currentTime = Calendar.getInstance();
				// 创建一个TimePickerDialog实例并显示出来
				new TimePickerDialog(MainActivity.this, 0,
						new TimePickerDialog.OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(MainActivity.this,
										AlarmActivity.class);
								PendingIntent pi = PendingIntent.getActivity(
										MainActivity.this, 0, intent, 0);
								Calendar c = Calendar.getInstance();
								c.setTimeInMillis(System.currentTimeMillis());
								c.set(Calendar.HOUR, hourOfDay);
								c.set(Calendar.MINUTE, minute);
								// 设置AlarmManager将在对应的时间启动指定组件
								alarmManager.set(AlarmManager.RTC_WAKEUP,
										c.getTimeInMillis(), pi);
								Toast.makeText(MainActivity.this, "闹铃设置成功啦～～～",
										Toast.LENGTH_SHORT).show();
							}
						}, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
								.get(Calendar.MINUTE), false).show();
			}
		});
	}
}