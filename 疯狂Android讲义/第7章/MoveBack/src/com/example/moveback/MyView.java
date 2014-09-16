package com.example.moveback;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;

public class MyView extends View {

	// 记录背景位图的实际高度 该高度必须不能大于图片高度
	final  int BACK_HEIGHT =1024;
	// 背景图片
	private Bitmap back;
	private Bitmap plane;
	// 背景图片开始的位置
	final int WIDTH = 320;
	final int HEIGHT = 600;
	private int startY = BACK_HEIGHT - HEIGHT;
	
	public MyView(Context context) {
		super(context);
		back = BitmapFactory.decodeResource(context.getResources(), R.drawable.back);
		plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		final Handler handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0x111) {
					// 重新开始移动  y must > 0
					if (startY <= 3) {
						startY = BACK_HEIGHT- HEIGHT;
					}else {
						startY -= 3;
					}
				}
				invalidate();
			};
		};
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x111);
			}
		}, 0, 100);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// 根据原始位图和Matrix创建新图片
		Bitmap bitmap2 = Bitmap.createBitmap(back, 0, startY, WIDTH, HEIGHT);
		// 绘制新位图
		canvas.drawBitmap(bitmap2, 0, 0,null);
		// 绘制飞机
		canvas.drawBitmap(plane, 160, 380, null);
	}

}
