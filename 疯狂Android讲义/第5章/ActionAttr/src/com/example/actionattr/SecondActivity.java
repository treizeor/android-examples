package com.example.actionattr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		TextView txt = (TextView)findViewById(R.id.textView1);
		String action = getIntent().getAction();
		txt.setText(action);
	}
}
