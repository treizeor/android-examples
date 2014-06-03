package com.example.ch003scrollview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button button1, button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.button1:
			intent.setClass(MainActivity.this, VerticalActivity.class);
			startActivity(intent);
			System.out.println(">>>>>>>>>>>>>>>>>>>button1");
			break;
		case R.id.button2:
			intent.setClass(MainActivity.this, HorizontalActivity.class);
			startActivity(intent);
			System.out.println(">>>>>>>>>>>>>>>>>>>button2");
			break;
		}
	}

}
