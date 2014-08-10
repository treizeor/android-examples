package com.example.calprime;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	final static String UPPER_NUM = "upper";
	CalThread calThread;
	EditText editNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editNum = (EditText) findViewById(R.id.editText1);
		Button calButton = (Button) findViewById(R.id.button1);
		calThread = new CalThread();
		calThread.start();

		calButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message msg = new Message();
				msg.what = 0x123;
				Bundle bundle = new Bundle();
				bundle.putInt(UPPER_NUM,
						Integer.parseInt(editNum.getText().toString()));
				msg.setData(bundle);
				calThread.mHandler.sendMessage(msg);
			}
		});
	}

	class CalThread extends Thread {
		public Handler mHandler;

		public void run() {
			Looper.prepare();
			mHandler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					if (msg.what == 0x123) {
						int upper = msg.getData().getInt(UPPER_NUM);
						List<Integer> nums = new ArrayList<Integer>();
						outer: for (int i = 2; i <= upper; i++) {
							for (int j = 2; j <= Math.sqrt(i); j++) {
								if (i != 2 && i % j == 0) {
									continue outer;
								}
							}
							nums.add(i);
						}
						Toast.makeText(MainActivity.this, nums.toString(),
								Toast.LENGTH_LONG).show();
					}
				};
			};
			Looper.loop();
		}
	}
}