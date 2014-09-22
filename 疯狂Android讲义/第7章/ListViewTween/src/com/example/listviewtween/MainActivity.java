package com.example.listviewtween;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ImageView imageView = (ImageView) findViewById(R.id.image);
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		// 获取屏幕宽和高
		display.getMetrics(metrics);
		// 设置对ImageView组件应用动画
		final MyAnimation animation = new MyAnimation(metrics.xdpi / 2,
				metrics.ydpi / 2, 3500);
		imageView.setAnimation(animation);
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 0x123) {
					imageView.startAnimation(animation);
				}
			}
		};

		imageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x123);
			}
		});

	}
}