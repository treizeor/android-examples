package com.example.datepickerdialogtest;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent.CanceledException;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button dateBtn = (Button) findViewById(R.id.button1);
		Button timeBtn = (Button) findViewById(R.id.button2);

		dateBtn.setOnClickListener(this);
		timeBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		switch (v.getId()) {
		case R.id.button1:
			new DatePickerDialog(MainActivity.this, new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					Toast.makeText(
							MainActivity.this,
							year + "年" + (monthOfYear + 1) + "月" + dayOfMonth
									+ "日", Toast.LENGTH_SHORT).show();
				}
			}, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH)).show();
			break;
		case R.id.button2:
			new TimePickerDialog(MainActivity.this, new OnTimeSetListener() {

				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					Toast.makeText(MainActivity.this,
							hourOfDay + "时" + minute + "分", Toast.LENGTH_LONG)
							.show();
				}
			}, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true).show();

			break;
		}
	}
}