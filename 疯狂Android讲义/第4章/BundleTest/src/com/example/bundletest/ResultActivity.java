package com.example.bundletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView name = (TextView)findViewById(R.id.name);
		TextView passwd = (TextView)findViewById(R.id.passwd);
		TextView gender = (TextView)findViewById(R.id.gender);
		Intent intent  =getIntent();
		Person p = (Person)intent.getSerializableExtra("person");
		name.setText(p.getName());
		passwd.setText(p.getPasswd());
		gender.setText(p.getGender());
	}
}
