package com.example.gestureflip;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnGestureListener {

	// ViewFlipper实例
	ViewFlipper flipper;
	// 手势检测器实例
	GestureDetector detector;
	// 动画数组, 用于为ViewFlipper指定切换动画效果
	Animation[] animations = new Animation[4];
	// 定义手势动作两点之间的最小距离
	final int FLIP_DISTANCE = 50;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		detector = new GestureDetector(this, this);
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		// 为ViewFlipper添加4个ImageView组件
		flipper.addView(addImageView(R.drawable.img1));
		flipper.addView(addImageView(R.drawable.img2));
		flipper.addView(addImageView(R.drawable.img3));
		flipper.addView(addImageView(R.drawable.img4));
		// 初始化Animation数组
		animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
		animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
		animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
		animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);

	}

	private View addImageView(int resId) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		return imageView;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		// 如果第一个触点事件的X坐标大于第二个触点事件的X坐标超过FLIP_DISTANCE，也就是手势想左滑
		if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
			// 为flipper 设置动画效果
			flipper.setInAnimation(animations[2]);
			flipper.setOutAnimation(animations[1]);
			flipper.showPrevious();
			return true;
		}
		// 向右滑
		if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
			flipper.setInAnimation(animations[0]);
			flipper.setOutAnimation(animations[3]);
			flipper.showNext();
			return true;
		}

		return false;
	}
}