package com.example.animatortest;

import java.util.ArrayList;

import android.R.integer;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	// 定义球的大小的常量
	static final float BALL_SIZE = 50F;
	// 定义小球从屏幕上方掉落到屏幕底端的总时间
	static final float FULL_TIME = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FrameLayout container = (FrameLayout)findViewById(R.id.container);
		container.addView(new MyAnimationView(this));

	}

	public class MyAnimationView extends View implements AnimatorUpdateListener {

		public final ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();

		public MyAnimationView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			setBackgroundColor(Color.WHITE);
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
			// 计算小球下落动画开始时的Y坐标
			float startY = newBall.getY();
			// 计算小球下落动画结束时的Y坐标，（落到屏幕最下方，就是屏幕高度减去小球高度)
			float endY = getHeight() - BALL_SIZE;
			// 获取屏幕高度
			float h = (float) getHeight();
			float eventY = event.getY();
			// 计算动画持续时间
			int duration = (int) (FULL_TIME * (h - eventY) / h);
			// 定义小球下落动画
			ValueAnimator fallAnim = ObjectAnimator.ofFloat(newBall, "y",
					startY, endY);
			// 设置fallAnim持续时间
			fallAnim.setDuration(duration);
			// 设置fallAnim动画的插值方式，加速插值
			fallAnim.setInterpolator(new AccelerateInterpolator());
			// 为fallAnim添加监听器
			// 当ValueAnimator的属性值发生改变是，将会激发该监听器的事件监听方法
			fallAnim.addUpdateListener(this);
			// 定义对newBall对象的alpha属性执行从1到0的动画，渐隐动画
			ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha",
					1f, 0f);
			// 设置动画持续时间
			fadeAnim.setDuration(250);
			// 为fadeAnim动画添加监听器
			fadeAnim.addListener(new AnimatorListenerAdapter() {
				// 当动画结束时
				@Override
				public void onAnimationEnd(Animator animation) {
					// 动画结束时将该动画关联的ShapeHolder删除
					balls.remove(((ObjectAnimator) animation).getTarget());
				}
			});
			// 为fadeAnim动画添加监听器
			// 当ValueAnimator的属性值发生改变时，将会激发该监听器的事件监听方法
			fadeAnim.addUpdateListener(this);
			// 定义一个AnimatorSet来组合动画
			AnimatorSet animatorSet = new AnimatorSet();
			animatorSet.play(fallAnim).before(fadeAnim);
			// 开始播放动画
			animatorSet.start();

			return true;

		}

		private ShapeHolder addBall(float x, float y) {
			// 创建一个圆
			OvalShape circle = new OvalShape();
			// 设置该椭圆的宽高
			circle.resize(BALL_SIZE, BALL_SIZE);
			// 将圆包装成Drawable对象
			ShapeDrawable drawable = new ShapeDrawable(circle);
			// 创建一个ShapeHolder对象
			ShapeHolder shapeHolder = new ShapeHolder(drawable);
			// 设置shapeHolder的x, y坐标
			shapeHolder.setX(x - BALL_SIZE / 2);
			shapeHolder.setY(y - BALL_SIZE / 2);
			int red = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			// 将red、green、blue三个随机数组合成ARGB颜色
			int color = 0xff000000 + red << 16 | green << 8 | blue;
			// 获取drawable上关联的画笔
			Paint paint = drawable.getPaint();
			// 将red、green、blue三个随机数除以4得到的商值组合成ARGB颜色
			int darkColor = 0xff000000 | red << 16 | green << 8 | blue;
			// 创建圆形渐变
			RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
					BALL_SIZE, color, darkColor, Shader.TileMode.CLAMP);
			paint.setShader(gradient);
			// 为shapeHolder设置paint画笔
			shapeHolder.setPaint(paint);
			balls.add(shapeHolder);
			return shapeHolder;

		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			// 遍历balls集合中的每个ShapeHolder对象
			for (ShapeHolder shapeHolder : balls) {
				// 保存cavas的当前坐标系统
				canvas.save();
				// 坐标变换: 将画布坐标系统平移到shapeHolder的X\Y坐标处
				canvas.translate(shapeHolder.getX(), shapeHolder.getY());
				// 将shapeHolder持有的圆形绘制到canvas上
				shapeHolder.getShape().draw(canvas);
				// 恢复Canva坐标系统
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