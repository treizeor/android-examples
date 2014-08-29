package com.example.attrrestest;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AlphaImageView extends ImageView {

	// 图像透明度每次改变的大小
	private int alphaDelta = 0;
	// 记录当前的透明度
	private int curAlpha = 0;
	// 每隔多少毫秒透明度改变一次
	private final int SPEED = 300;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				curAlpha += alphaDelta;
				if (curAlpha > 255) {
					curAlpha = 255;
				}
				AlphaImageView.this.setAlpha(curAlpha);
			}
		};
	};

	public AlphaImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.AlphaImageView);
		int duration = typedArray
				.getInt(R.styleable.AlphaImageView_duration, 0);
		alphaDelta = 255 * SPEED / duration;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		this.setAlpha(curAlpha);
		super.onDraw(canvas);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = new Message();
				msg.what = 0x123;
				if (curAlpha >= 255) {
					timer.cancel();
				} else {
					handler.sendMessage(msg);
				}
			}
		}, 0, SPEED);
	}

}
