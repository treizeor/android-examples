package com.example.sendsms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText number, content;
	Button send;
	SmsManager smsManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取SmsManager
		smsManager = SmsManager.getDefault();
		number = (EditText) findViewById(R.id.editText1);
		content = (EditText) findViewById(R.id.editText2);
		send = (Button) findViewById(R.id.button1);
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 创建一个PendingIntent对象
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this,
						0, new Intent(), 0);
				// 发送短信
				smsManager.sendTextMessage(number.getText().toString(), null,
						content.getText().toString(), pi, null);
				Toast.makeText(MainActivity.this, "短信发送完成", 0).show();

			}
		});
	}
}