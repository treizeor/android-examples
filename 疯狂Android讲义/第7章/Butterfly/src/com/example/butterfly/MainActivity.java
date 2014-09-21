package com.example.butterfly;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	// 记录蝴蝶ImageView当前位置
	private float curX;
	private float curY;

	// 下一个位置
	float nextX = 0;
	float nextY = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView imageView = (ImageView) findViewById(R.id.imageView);
		final Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0x123) {
					// 横向上一张向右飞
					if (nextX > 720) {
						curX = nextX = 0;
					} else {
						nextX += 10;
					}
					// 纵向随机上下
					nextY = curY + (float) (Math.random() * 10 - 5);
					// 设置显示蝴蝶的ImageView发生位移改变
					TranslateAnimation anim = new TranslateAnimation(curX,
							nextX, curY, nextY);
					curX = nextX;
					curY = nextY;
					anim.setDuration(200);
					imageView.startAnimation(anim);
				}
			};
		};

		final AnimationDrawable butterfly = (AnimationDrawable) imageView
				.getBackground();
		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				butterfly.start();
				new Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						handler.sendEmptyMessage(0x123);
					}
				}, 0, 200);
			}
		});
	}
}