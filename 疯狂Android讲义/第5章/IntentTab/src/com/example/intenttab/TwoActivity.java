package com.example.intenttab;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TwoActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		TextView tv = new TextView(this);
		tv.setText(this.toString());
		layout.addView(tv);
		setContentView(layout);
	}
}
