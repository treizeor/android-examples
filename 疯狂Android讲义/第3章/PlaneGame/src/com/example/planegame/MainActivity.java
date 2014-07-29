package com.example.planegame;

import com.example.plaingame.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	private int speed = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 创建PlaneView组件
		final PlaneView planeView = new PlaneView(this);
		setContentView(planeView);
		planeView.setBackgroundResource(R.drawable.back);
		// 获取窗口管理器
		WindowManager wm = getWindowManager();
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		// 获得屏幕宽高
		display.getMetrics(metrics);
		//设置飞机初始位置
		planeView.currentX = metrics.widthPixels / 2;
		planeView.currentY = metrics.heightPixels - 40;
		// 为draw组件键盘事件绑定监听器
		planeView.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				//获取由那个按键促发的事件
				switch (event.getKeyCode()) {
				case KeyEvent.KEYCODE_S:
					planeView.currentY += speed;
					break;
				case KeyEvent.KEYCODE_W:
					planeView.currentY -= speed;
					break;
				case KeyEvent.KEYCODE_A:
					planeView.currentX -= speed;
					break;
				case KeyEvent.KEYCODE_D:
					planeView.currentX += speed;
					break;

				}
				planeView.invalidate();
				return true;
			}
		});
	}
}