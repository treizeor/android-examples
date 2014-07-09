package com.example.calendarviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CalendarView calendar = (CalendarView) findViewById(R.id.canendarview);
		calendar.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"" + year + "/" + (month + 1) + "/" + dayOfMonth,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}