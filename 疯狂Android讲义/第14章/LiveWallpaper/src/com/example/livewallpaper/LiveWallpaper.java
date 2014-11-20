package com.example.livewallpaper;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class LiveWallpaper extends WallpaperService {

	// 记录用户触碰点点位图
	private Bitmap heart;

	@Override
	public Engine onCreateEngine() {
		// TODO Auto-generated method stub
		// 加载图片
		heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
		// 返回自定义的Engine
		return new MyEngine();
	}

	class MyEngine extends Engine {
		// 记录程序界面是否可见
		private boolean mVisible;
		// 记录当前用户动作事件发生的位置
		private float mTouchX = -1;
		private float mTouchY = -1;

		// 记录当前需要绘制的矩形的数量
		private int count = -1;
		// 记录绘制第一个矩形所需要坐标变换的XY偏移
		private int originX = 50, originY = 50;
		// 定义画笔
		private Paint mPaint = new Paint();
		// 定义一个Handler
		Handler mHandler = new Handler();
		// 定义一个周期性执行的任务
		private final Runnable drawTarget = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				drawFrame();
			}
		};

		public void onCreate(android.view.SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
			// 初始化画笔
			mPaint.setARGB(76, 0, 0, 255);
			mPaint.setAntiAlias(true);
			mPaint.setStyle(Paint.Style.FILL);
			// 设置触摸处理事件
			setTouchEventsEnabled(true);
		};

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			// 删除回调
			mHandler.removeCallbacks(drawTarget);
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			// TODO Auto-generated method stub
			super.onVisibilityChanged(visible);
			mVisible = visible;
			// 当用户界面可见时
			if (visible) {
				drawFrame();
			} else {
				mHandler.removeCallbacks(drawTarget);
			}
		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset,
				float xOffsetStep, float yOffsetStep, int xPixelOffset,
				int yPixelOffset) {
			// TODO Auto-generated method stub
			drawFrame();
		}

		@Override
		public void onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			// 如果检测到有滑动操作
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				mTouchX = event.getX();
				mTouchY = event.getY();
			} else {
				mTouchX = -1;
				mTouchY = -1;
			}
			super.onTouchEvent(event);
		}

		// 定义绘图工具的方法
		private void drawFrame() {
			// 获取该壁纸的SurfaceHolder
			final SurfaceHolder holder = getSurfaceHolder();
			Canvas c = null;
			try {
				// 对画布加锁
				c = holder.lockCanvas();
				if (c != null) {
					// 绘制背景色
					c.drawColor(0xffffffff);
					// 在触碰点绘制心形
					drawTouchPoint(c);
					// 设置画笔透明度
					mPaint.setAlpha(76);
					c.translate(originX, originY);
					// 采用循环绘制count个矩形
					for (int i = 0; i < count; i++) {
						c.translate(80, 0);
						c.scale(0.95f, 0.95f);
						c.rotate(20f);
						c.drawRect(0, 0, 150, 75, mPaint);
					}
				}
			} finally {
				if (c != null)
					holder.unlockCanvasAndPost(c);
			}
			mHandler.removeCallbacks(drawTarget);
			// 调度下一次重绘
			if (mVisible) {
				count++;
				if (count >= 50) {
					Random rand = new Random();
					count = 1;
					originX += (rand.nextInt(60) - 30);
					originY += (rand.nextInt(60) - 30);
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				// 指定0.1秒后重新执行mDrawCube一次
				mHandler.postDelayed(drawTarget, 100);
			}
		}

		// 在屏幕触碰点绘制圆圈
		private void drawTouchPoint(Canvas c) {
			// TODO Auto-generated method stub
			if (mTouchX >= 0 && mTouchY >= 0) {
				mPaint.setAlpha(255);
				c.drawBitmap(heart, mTouchX, mTouchY, mPaint);
			}
		}
	}
}
