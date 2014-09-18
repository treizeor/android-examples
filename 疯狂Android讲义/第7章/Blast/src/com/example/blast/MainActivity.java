package com.example.blast;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	private MyView myView;
	private MediaPlayer bomb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 使用FrameLayout，它允许组件自己控制位置
		FrameLayout frame = new FrameLayout(this);
		setContentView(frame);
		// 设置使用背景
		frame.setBackgroundResource(R.drawable.img4);
		// 加载音效
		bomb = MediaPlayer.create(this, R.raw.ring);
		myView = new MyView(this);
		// 设置myView用于显示blast动画
		myView.setBackgroundResource(R.anim.blast);
		// 设置myView默认隐藏
		myView.setVisibility(View.INVISIBLE);
		// 获取动画对象
		myView.anim = (AnimationDrawable) myView.getBackground();
		frame.addView(myView);

		// 触摸事件
		frame.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				// 只处理按下事件，避免产生两次动画
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					// 先停止动画播放
					myView.anim.stop();
					float x = event.getX();
					float y = event.getY();
					// 控制myView的显示位置
					myView.setLocation((int) x - 20, (int) y - 40);
					myView.setVisibility(View.VISIBLE);
					// 启动动画
					myView.anim.start();
					// 播放音效
					bomb.start();
				}
				return false;
			}
		});

	}
}