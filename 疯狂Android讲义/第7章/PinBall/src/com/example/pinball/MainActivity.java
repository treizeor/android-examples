package com.example.pinball;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	// 桌面宽高
	private int tableWidth;
	private int tableHeight;
	// 球拍垂直位置
	private int racketY;
	// 球拍的高度和宽度
	private final int RACKET_HEIGHT = 30;
	private final int RACKET_WIDTH = 70;
	// 小球大小
	private final int BALL_SIZE = 12;
	// 小球纵向的运行速度
	private int ySpeed = 10;
	Random rand = new Random();
	// 返回一个-0.5~0.5的比率，用于控制小球的运行方向
	private double xyRate = rand.nextDouble() - 0.5;
	// 小球横向运行的速度
	private int xSpeed = (int) (ySpeed * xyRate * 2);
	// ballX,ballY代表小球的坐标
	private int ballX = rand.nextInt(200) + 20;
	private int ballY = rand.nextInt(10) + 20;
	// racketX代表球拍水平位置
	private int racketX = rand.nextInt(200);
	// 游戏是否结束的标识
	private boolean isLose = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉窗口标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 创建GameView组件
		final GameView gameView = new GameView(this);
		setContentView(gameView);

		// 获取窗口管理器
		WindowManager wm = getWindowManager();
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		// 获得屏幕宽和高
		tableWidth = metrics.widthPixels;
		tableHeight = metrics.heightPixels;
		racketY = tableHeight - 80;
		final Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				if (msg.what == 0x111) {
					gameView.invalidate();
				}
			};
		};

		gameView.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				switch (event.getKeyCode()) {
				// 左移
				case KeyEvent.KEYCODE_A:
					if (racketX > 0) {
						racketX -= 10;
					}
					break;
				// 右移
				case KeyEvent.KEYCODE_D:
					if (racketX < tableWidth - RACKET_WIDTH) {
						racketX += 10;
					}
					break;
				}
				gameView.invalidate();
				return true;
			}
		});

		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// 如果小球碰到左右边框
				if (ballX <= 0 || ballX >= tableWidth - BALL_SIZE) {
					xSpeed = -xSpeed;
				}
				// 如果小球高度超出球拍位置，且横向不在球拍范围内，游戏结束
				if (ballY >= racketY - BALL_SIZE
						&& (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {
					timer.cancel();
					isLose = true;
				}
				// 如果小球到达顶部，或小球碰到球拍，小球反弹
				else if (ballY <= 0
						|| (ballY >= racketY - BALL_SIZE && ballX > racketX && ballX < racketX
								+ RACKET_WIDTH)) {
					ySpeed = -ySpeed;
				}
				// 小球坐标增加
				ballX += xSpeed;
				ballY += ySpeed;
				// 发送消息，通知系统重绘
				handler.sendEmptyMessage(0x111);
			}
		}, 0, 100);

	}

	class GameView extends View {
		Paint paint = new Paint();

		public GameView(Context context) {
			super(context);
			setFocusable(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			paint.setStyle(Paint.Style.FILL);
			paint.setAntiAlias(true);
			if (isLose) {
				paint.setColor(Color.RED);
				paint.setTextSize(40);
				canvas.drawText("Game Over!!!", 50, 200, paint);
			} else {
				paint.setColor(Color.rgb(240, 240, 80));
				canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);
				paint.setColor(Color.rgb(80, 80, 200));
				canvas.drawRect(racketX, racketY, racketX + RACKET_WIDTH,
						racketY + RACKET_HEIGHT, paint);
			}
		}
	}
}