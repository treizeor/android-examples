package com.example.progressdialogtest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends Activity {
	final static int MAX_PROGRESS = 100;
	private int[] data = new int[50];
	int progerssStatus = 0;
	int hasData = 0;
	ProgressDialog pd1, pd2;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
				pd2.setProgress(progerssStatus);
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// Button1
	public void showSpinner(View view) {
		// 调用静态方法显示环形进度条
		ProgressDialog.show(MainActivity.this, "任务执行中", "任务执行中，请等待", false,
				true);
	}

	// Button2
	public void showIndeterminate(View view) {
		pd1 = new ProgressDialog(MainActivity.this);
		pd1.setTitle("任务正在进行中");
		pd1.setMessage("任务正在执行中，请等待...");
		// 设置对话框能点击框外关闭
		pd1.setCancelable(true);
		// 设置进度条风格
		pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// 设置对话框是否显示进度
		pd1.setIndeterminate(true);
		pd1.show();
	}

	// Button3
	public void showProgress(View view) {
		progerssStatus = 0;
		hasData = 0;
		pd2 = new ProgressDialog(MainActivity.this);
		pd2.setMax(MAX_PROGRESS);
		pd2.setTitle("任务完成百分比");
		pd2.setMessage("耗时任务进度");
		pd2.setCancelable(false);
		pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd2.setIndeterminate(false);
		pd2.show();
		new Thread() {
			public void run() {
				while (progerssStatus < MAX_PROGRESS) {
					progerssStatus = MAX_PROGRESS * doWork() / data.length;
					handler.sendEmptyMessage(0x123);
				}
				pd2.dismiss();
			}
		}.start();
	}

	public int doWork() {
		data[hasData++] = (int) (Math.random() * 100);
		// System.out.println(hasData);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hasData;
	}
}