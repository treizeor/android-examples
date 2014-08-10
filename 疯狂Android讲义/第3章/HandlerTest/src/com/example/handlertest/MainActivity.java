package com.example.handlertest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends Activity {

	int[] imgIds = new int[] { R.drawable.img1, R.drawable.img2,
			R.drawable.img3, R.drawable.img4, };
	int currentImgId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView show = (ImageView) findViewById(R.id.imageView1);
		final Handler myHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0x123) {
					show.setImageResource(imgIds[currentImgId++ % imgIds.length]);
				}
			}
		};

		// 定义一个定时器, 周期性执行指定任务
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				myHandler.sendEmptyMessage(0x123);
			}
		}, 0, 1200);
	}
}