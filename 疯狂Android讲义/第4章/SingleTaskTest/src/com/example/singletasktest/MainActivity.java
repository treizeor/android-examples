package com.example.singletasktest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(MainActivity.this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		TextView tv = new TextView(this);
		tv.setText("Activity为:" + this.toString() + "\nTask ID:"
				+ this.getTaskId());
		Button button = new Button(MainActivity.this);
		button.setText("Start SecondActivity");
		layout.addView(tv);
		layout.addView(button);

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}
}