package com.example.chronometer;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class MainActivity extends Activity {

	Chronometer chronometer;
	Button start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		chronometer = (Chronometer) findViewById(R.id.chronometer1);
		start = (Button) findViewById(R.id.button1);
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chronometer.setBase(SystemClock.elapsedRealtime());
				chronometer.start();
				start.setEnabled(false);
			}
		});
		chronometer
				.setOnChronometerTickListener(new OnChronometerTickListener() {

					@Override
					public void onChronometerTick(Chronometer chronometer) {
						// TODO Auto-generated method stub
						if (SystemClock.elapsedRealtime()
								- chronometer.getBase() > 20 * 1000) {
							chronometer.stop();
							start.setEnabled(true);
						}
					}
				});
	}
}