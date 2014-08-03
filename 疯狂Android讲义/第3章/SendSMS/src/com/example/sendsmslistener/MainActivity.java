package com.example.sendsmslistener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText phoneNum, content;
	Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		phoneNum = (EditText) findViewById(R.id.phoneNum);
		content = (EditText) findViewById(R.id.content);
		send = (Button) findViewById(R.id.send);

		send.setOnClickListener(new SendSmsListener(this, phoneNum, content));
	}

}