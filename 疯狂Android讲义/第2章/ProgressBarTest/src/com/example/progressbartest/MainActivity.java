package com.example.progressbartest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	int status = 0;
	ProgressBar bar1, bar2, progressBar;
	// 创建一个负责更新的进度的Handler
	Handler mHandler2 = new Handler();
	Runnable add = new Runnable() {
		public void run() {
			status = progressBar.getProgress() + 1;
			progressBar.setProgress(status);
			if (status < 100) {
				mHandler2.postDelayed(add, 50);
			}
		}
	};

	// handleMessage方式
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x111) {
				bar2.setProgress(status);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar1 = (ProgressBar) findViewById(R.id.bar1);
		bar2 = (ProgressBar) findViewById(R.id.bar2);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		// post方式更新progressbar
		mHandler2.post(add);
		
		// sendmessage方式更新progressbar
		new Thread() {
			public void run() {
				while (status < 100) {
					try {
						Thread.sleep(100);
						status++;
						mHandler.sendEmptyMessage(0x111);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}.start();

	}
}