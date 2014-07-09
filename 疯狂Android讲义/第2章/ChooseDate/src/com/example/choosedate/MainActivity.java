package com.example.choosedate;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {

	private int year, month, day, hour, minute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

		// 获取当前年月日小时分钟
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH)+1;
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR);
		minute = c.get(Calendar.MINUTE);
		showDate(year, month, day, hour, minute);

		// 初始化DatePicker组件，初始化时指点监听器
		datePicker.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				// TODO Auto-generated method stub
				MainActivity.this.day = dayOfMonth;
				MainActivity.this.month = monthOfYear;
				MainActivity.this.year = year;
				showDate(year, month, day, hour, minute);
			}
		});
		
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				MainActivity.this.hour = hourOfDay;
				MainActivity.this.minute = minute;
				showDate(year, month, day, hour, minute);
			}
		});

	}

	private void showDate(int year, int month, int day, int hour, int minute) {
		TextView textView = (TextView) findViewById(R.id.showTime);
		textView.setText("你选择的日期为:" + year + "年" + month + "月" + day + "日"
				+ hour + "时" + minute + "分");
	}
}