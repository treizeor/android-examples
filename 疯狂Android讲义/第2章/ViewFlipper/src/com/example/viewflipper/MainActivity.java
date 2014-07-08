package com.example.viewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	ViewFlipper viewFlipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewFlipper = (ViewFlipper) findViewById(R.id.details);
	}

	public void prev(View v) {
		viewFlipper.setInAnimation(MainActivity.this, R.anim.slide_in_right);
		viewFlipper.setOutAnimation(MainActivity.this, R.anim.slide_out_left);
		viewFlipper.showPrevious();
		viewFlipper.stopFlipping();
	}

	public void next(View v) {
		viewFlipper.setInAnimation(MainActivity.this,
				android.R.anim.slide_in_left);
		viewFlipper.setOutAnimation(MainActivity.this,
				android.R.anim.slide_out_right);
		viewFlipper.showNext();
		viewFlipper.stopFlipping();
	}

	public void auto(View v) {
		viewFlipper.setInAnimation(MainActivity.this,
				android.R.anim.slide_in_left);
		viewFlipper.setOutAnimation(MainActivity.this,
				android.R.anim.slide_out_right);
		viewFlipper.startFlipping();
	}
}