package com.example.bouncingballs;

import java.util.ArrayList;

import android.R.integer;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	// 定义小球大小
	static final float BALL_SIZE = 50F;
	// 定义小球从屏幕上方掉落到屏幕底端的总时间
	static final float FULL_TIME = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FrameLayout container = (FrameLayout) findViewById(R.id.container);
		container.addView(new MyAnimationView(this));
	}

	public class MyAnimationView extends View implements AnimatorUpdateListener {
		public final ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();

		public MyAnimationView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			// 加载动画资源
			ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater
					.loadAnimator(getContext(), R.animator.color_anim);
			colorAnim.setEvaluator(new ArgbEvaluator());
			colorAnim.setTarget(this);
			colorAnim.start();
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			// 如果触碰事件不是按下、移动事件
			if (event.getAction() != MotionEvent.ACTION_DOWN
					&& event.getAction() != MotionEvent.ACTION_MOVE) {
				return false;
			}
			// 在事件发生点添加一个小球
			ShapeHolder newBall = addBall(event.getX(), event.getY());
			// 计算小球下落动画开始和结束时的Y坐标
			float startY = newBall.getY();
			float endY = getHeight() - BALL_SIZE;
			// 获取屏幕高度
			float h = (float) getHeight();
			float eventY = event.getY();
			// 计算动画持续时间
			int duration = (int) (FULL_TIME * (h - eventY) / h);
			// 定义小球落下动画
			ValueAnimator fallAnim = ObjectAnimator.ofFloat(newBall, "y",
					startY, endY);
			// 设置fallAnim动画持续时间
			fallAnim.setDuration(duration);
			// 设置动画插值方式:加速插值
			fallAnim.setInterpolator(new AccelerateInterpolator());
			// 定义小球“压扁"的动画:该动画控制小球的x坐标”向左移“半个球
			ValueAnimator squashAnim1 = ObjectAnimator.ofFloat(newBall, "x",
					newBall.getX(), newBall.getX() - BALL_SIZE / 2);
			// 设置squashAnim1动画持续时间
			squashAnim1.setDuration(duration / 4);
			// 设置squashAnim1动画重复1次
			squashAnim1.setRepeatCount(1);
			// 重复方式
			squashAnim1.setRepeatMode(ValueAnimator.REVERSE);
			// 插值方式：减速插值
			squashAnim1.setInterpolator(new DecelerateInterpolator());

			// 定义小球压扁动画2：该动画控制小球的宽度加倍
			ValueAnimator squashAnim2 = ObjectAnimator
					.ofFloat(newBall, "width", newBall.getWidth(),
							newBall.getWidth() + BALL_SIZE);
			squashAnim2.setDuration(duration / 4);
			squashAnim2.setRepeatCount(1);
			squashAnim2.setRepeatMode(ValueAnimator.REVERSE);
			squashAnim2.setInterpolator(new DecelerateInterpolator());

			// 定义小球拉伸动画1:该动画控制小球的y坐标“向下移”半个球
			ObjectAnimator stretchAnim1 = ObjectAnimator.ofFloat(newBall, "y",
					endY, endY + BALL_SIZE / 2);
			stretchAnim1.setDuration(duration / 4);
			stretchAnim1.setRepeatCount(1);
			stretchAnim1.setRepeatMode(ValueAnimator.REVERSE);
			stretchAnim1.setInterpolator(new DecelerateInterpolator());

			// 定义小球拉伸动画2:该动画控制小球高度减半
			ValueAnimator stretchAnim2 = ObjectAnimator.ofFloat(newBall,
					"height", newBall.getHeight() - BALL_SIZE / 2);
			stretchAnim2.setDuration(duration / 4);
			stretchAnim2.setRepeatCount(1);
			stretchAnim2.setRepeatMode(ValueAnimator.REVERSE);
			stretchAnim2.setInterpolator(new DecelerateInterpolator());

			// 定义小球弹起的动画
			ObjectAnimator bounceBackAnim = ObjectAnimator.ofFloat(newBall,
					"y", endY, startY);
			bounceBackAnim.setDuration(duration);
			bounceBackAnim.setInterpolator(new DecelerateInterpolator());

			// 使用AnimatorSet按顺序播放“掉落、压扁&拉伸、弹起”动画
			AnimatorSet bouncer = new AnimatorSet();
			bouncer.play(fallAnim).before(squashAnim1);
			// 指定同时播放squashAnim1、squashAnim2、stretchAnim1、stretchAnim2
			bouncer.play(squashAnim1).with(squashAnim2);
			bouncer.play(squashAnim1).with(stretchAnim1);
			bouncer.play(squashAnim1).with(stretchAnim2);
			// 播放弹起动画
			bouncer.play(bounceBackAnim).after(stretchAnim2);

			// 定义渐隐动画
			ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha",
					1f, 0f);
			fadeAnim.setDuration(250);

			// 为fadeAnim添加动画监听器
			fadeAnim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					super.onAnimationEnd(animation);
					// 动画结束时将该动画关联的shapeHolder删除
					balls.remove(((ObjectAnimator) animation).getTarget());
				}
			});

			// 再次定义一个AnimatorSet来组合动画
			AnimatorSet animatorSet = new AnimatorSet();
			animatorSet.play(bouncer).before(fadeAnim);
			// 开始播放动画
			animatorSet.start();

			fallAnim.addUpdateListener(this);
			squashAnim1.addUpdateListener(this);
			squashAnim2.addUpdateListener(this);
			stretchAnim1.addUpdateListener(this);
			stretchAnim2.addUpdateListener(this);
			bounceBackAnim.addUpdateListener(this);
			fadeAnim.addUpdateListener(this);

			return true;
		}

		private ShapeHolder addBall(float x, float y) {
			// TODO Auto-generated method stub
			// 创建一个圆
			OvalShape circle = new OvalShape();
			circle.resize(BALL_SIZE, BALL_SIZE);
			// 将圆包装成Drawable对象
			ShapeDrawable drawable = new ShapeDrawable(circle);
			// 创建一个ShapeHolder对象
			ShapeHolder shapeHolder = new ShapeHolder(drawable);
			// 设置shapeHolder坐标
			shapeHolder.setX(x - BALL_SIZE / 2);
			shapeHolder.setY(y - BALL_SIZE / 2);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			int color = 0xff000000 | red << 16 | green << 8 | blue;
			// 获取drawable上关联的画笔
			Paint paint = drawable.getPaint();
			int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue
					/ 4;
			// 创建圆形渐变
			RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
					BALL_SIZE, color, darkColor, Shader.TileMode.CLAMP);
			paint.setShader(gradient);
			shapeHolder.setPaint(paint);
			balls.add(shapeHolder);
			return shapeHolder;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			for (ShapeHolder shapeHolder : balls) {
				// 保存当前的坐标系统
				canvas.save();
				// 坐标变换，将画布坐标系统平移到shapeHolder的X、Y坐标处
				canvas.translate(shapeHolder.getX(), shapeHolder.getY());
				// 将shapeHolder持有的圆形绘制在canvas上
				shapeHolder.getShape().draw(canvas);
				// 恢复坐标系统
				canvas.restore();
			}
		}

		@Override
		public void onAnimationUpdate(ValueAnimator animation) {
			// TODO Auto-generated method stub
			this.invalidate();
		}

	}
}