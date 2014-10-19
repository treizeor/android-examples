package com.example.sortedbroadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button send = (Button) findViewById(R.id.button1);
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("cn.treize.action.MY_BROADCAST"); // 也传播到了前一个例子的Receiver，同action
				intent.putExtra("msg", "简单的消息2");
				sendOrderedBroadcast(intent, null);
			}
		});
	}
}