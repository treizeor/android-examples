package com.example.addshortcut;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView flower;
	// 定义两份动画资源
	Animation anim, reverse;
	final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				flower.startAnimation(reverse);
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		flower = (ImageView)findViewById(R.id.imageView1);
		// 加载动画资源并设置动画结束后保留结束状态
		anim = AnimationUtils.loadAnimation(this, R.anim.anim);
		anim.setFillAfter(true);
		reverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
		reverse.setFillAfter(true);
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 创建添加快捷方式的Intent
				Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
				String title = getResources().getString(R.string.title);
				// 加载快捷方式的图标
				Parcelable icon = Intent.ShortcutIconResource.fromContext(MainActivity.this, R.drawable.heart);
				// 创建点击快捷方式后操作Intent，该处为启动该程序
				Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
				// 设置快捷方式的标题
				addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
				// 设置快捷方式图标
				addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
				// 设置快捷方式对于的Intent
				addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);
				// 发送广播添加快捷方式
				sendBroadcast(addIntent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 开始执行动画
		flower.startAnimation(anim);
		// 设置3.5秒后执行第二个动画
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x123);
			}
		}, 3500);
	}
}