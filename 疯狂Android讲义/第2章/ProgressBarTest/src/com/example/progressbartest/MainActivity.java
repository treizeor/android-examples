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
	Handler mHandler = new Handler();
	Runnable add = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			status = progressBar.getProgress() + 1;
			progressBar.setProgress(status);
			if (status < 100) {
				mHandler.postDelayed(add, 50);
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar1 = (ProgressBar)findViewById(R.id.bar1);
		bar2 = (ProgressBar)findViewById(R.id.bar2);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		mHandler.post(add);
	}
}