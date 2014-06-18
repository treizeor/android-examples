package com.example.checkbuttontest;

import javax.security.auth.PrivateCredentialPermission;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RadioGroup rg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rg = (RadioGroup)findViewById(R.id.rg1);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				String tipString = checkedId == R.id.male ? "您的性别是男" : "您的性别是女";
				Toast.makeText(MainActivity.this, tipString, Toast.LENGTH_LONG).show();
			}
		});

	}
}