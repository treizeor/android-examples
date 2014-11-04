package com.example.multithreaddown;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	EditText url;
	EditText target;
	Button down;
	ProgressBar bar;

	DownUtil downUtil;
	private int mDownStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		url = (EditText) findViewById(R.id.url);
		target = (EditText) findViewById(R.id.target);
		down = (Button) findViewById(R.id.down);
		bar = (ProgressBar) findViewById(R.id.bar);

		final Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0x123) {
					bar.setProgress(mDownStatus);
				}
			};
		};

		down.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 初始化DownUtil对象, 最后一个参数指定线程数
				downUtil = new DownUtil(url.getText().toString(), target
						.getText().toString(), 2);
				new Thread() {
					public void run() {
						try {
							// 开始下载
							downUtil.download();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

						// 定义每秒调度获取一次下载的完成度
						final Timer timer = new Timer();
						timer.schedule(new TimerTask() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								double completeRate = downUtil
										.getCompleteRate();
								mDownStatus = (int) (completeRate * 100);
								handler.sendEmptyMessage(0x123);
								// 下载完成后取消任务调度
								if (mDownStatus >= 100) {
									timer.cancel();
								}
							}
						}, 0, 100);
					};
				}.start();
			}
		});

	}

}