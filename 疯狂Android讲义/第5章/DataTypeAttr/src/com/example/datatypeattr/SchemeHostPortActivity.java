package com.example.datatypeattr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SchemeHostPortActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		TextView tv = new TextView(this);
		layout.addView(tv);
		setContentView(layout);
		tv.setText(this.toString());
	}
}
