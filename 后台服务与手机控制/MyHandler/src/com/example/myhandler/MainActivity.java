package com.example.myhandler;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final static int PROGRESS_END = -1;
	private ProgressBar progressBar;
	private int progress;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case PROGRESS_END:
				Toast.makeText(MainActivity.this, "loading finish", 0).show();
				break;
			default:
				progressBar.setProgress(msg.what);

			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (progress < 100) {
					try {
						Thread.sleep(100);
						countProgress();
						progress++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				endProgress();
			}
		}).start();

	}
	
	private void countProgress() {
		// 获取Message对象
		Message msg = Message.obtain();
		// 将自定义的信息code封装入message对象
		msg.what = progress;
		// 发送message对象
		handler.sendMessage(msg);
	}
	
	private void endProgress(){
		Message msg = Message.obtain();
		msg.what = PROGRESS_END;
		handler.sendMessage(msg);
	}
}
