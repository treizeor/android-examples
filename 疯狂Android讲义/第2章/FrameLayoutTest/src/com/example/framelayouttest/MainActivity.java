package com.example.framelayouttest;

/*
 * 帧布局の霓虹灯效果
 */

import java.util.Timer;
import java.util.TimerTask;

import org.w3c.dom.Text;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int curretColor = 0;

	// 定义一个颜色数组（资源位于colors.xml文件中）
	final int[] colors = new int[] { R.color.color1, R.color.color2,
			R.color.color3, R.color.color4, R.color.color5, R.color.color6 };
	// 定义TextView的ID数组
	final int[] textViewId = new int[] { R.id.textView1, R.id.textView2,
			R.id.textView3, R.id.textView4, R.id.textView5, R.id.textView6 };

	TextView[] views = new TextView[textViewId.length];

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				for (int i = 0; i < textViewId.length; i++) {
					views[i].setBackgroundResource(colors[(i + curretColor)
							% textViewId.length]);
				}
				curretColor++;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		for (int i = 0; i < textViewId.length; i++) {
			views[i] = (TextView) findViewById(textViewId[i]);
		}
		// 定义一个线程周期性的改变currentColor的值
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// 发送一条空消息通知系统改变这六个TextView的背景颜色
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 300);
	}

}
