package com.example.ch003layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LinearActivity extends Activity implements OnClickListener {

	private Button linearButton, frameButton, absButton, relativeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_main);

		linearButton = (Button) findViewById(R.id.linear);
		frameButton = (Button) findViewById(R.id.frame);
		relativeButton = (Button) findViewById(R.id.relative);
		absButton = (Button) findViewById(R.id.abs);

		linearButton.setOnClickListener(this);
		frameButton.setOnClickListener(this);
		relativeButton.setOnClickListener(this);
		absButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.linear:
			intent.setClass(this, LinearActivity.class);
			break;
		case R.id.frame:
			intent.setClass(this, FrameActivity.class);
			break;
		case R.id.relative:
			intent.setClass(this, RelativeActivity.class);
			break;
		case R.id.abs:
			intent.setClass(this, AbsActivity.class);
			break;
		}
		startActivity(intent);
		finish();

	}
}
