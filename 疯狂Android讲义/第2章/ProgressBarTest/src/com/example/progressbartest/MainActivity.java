package com.example.progressbartest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	// 该程序模拟填充长度为100的数组
//	int[] data = new int[100];
//	int hasData = 0;
	private int progress = 0;
	int status = 0;
	ProgressBar bar1, bar2, progressBar;
	// 创建一个负责更新的进度的Handler
	Handler mHandler = new Handler(){
		public void handlerMessage(Message msg) {
			if (msg.what == 0x123) {
				bar1.setProgress(status);
				bar2.setProgress(status);
				progressBar.setProgress(status);
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
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (progress < 100) {
					try {
						Thread.sleep(100);
						mHandler.sendEmptyMessage(0x123);
						progress++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	
//	public int doWork() {
////		data[hasDate++] = (int) (Math.random()*100);
//		try {
//			Thread.sleep(100);
//			hasData++;
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return hasData;
//	}
}