package com.example.standardtest;

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
		this.setContentView(layout);
		TextView tv = new TextView(MainActivity.this);
		tv.setText("Activity为:"+this.toString()+"\nTask ID为:"+this.getTaskId());
		Button button = new Button(MainActivity.this);
		button.setText("启动自身");
		layout.addView(tv);
		layout.addView(button);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}