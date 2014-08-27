package com.example.clipdrawabletest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView imageView =(ImageView)findViewById(R.id.imgView);
		//获取图片所显示的ClipDrawable对象
		final ClipDrawable drawable = (ClipDrawable)imageView.getDrawable();
		final Handler handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0x123) {
					// 修改ClipDrawable的level值
					drawable.setLevel(drawable.getLevel() + 200);
				}
			};
		};
		
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = new Message();
				msg.what = 0x123;
				handler.sendMessage(msg);
				if (drawable.getLevel() == 10000){
					timer.cancel();
				}
			}
		}, 0, 200);
	}
}