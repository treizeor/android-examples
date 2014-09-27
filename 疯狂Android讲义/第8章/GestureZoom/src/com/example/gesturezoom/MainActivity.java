package com.example.gesturezoom;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnGestureListener {
	GestureDetector detector;
	ImageView imageView;
	// 初始的图片资源
	Bitmap bitmap;
	// 定义图片的宽高
	int width, height;
	// 记录当前的缩放比例
	float currentScale = 1;
	// 控制图片缩放的Matrix对象
	Matrix matrix;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建手势检测器
		detector = new GestureDetector(this, this);
		imageView = (ImageView) findViewById(R.id.imageView1);
		matrix = new Matrix();
		// 获取被缩放的源图片
		bitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.ic_launcher);
		// 获得位图宽
		width = bitmap.getWidth();
		// 获得位图高
		height = bitmap.getHeight();
		// 设置Image初始化时显示的图片
		imageView.setImageBitmap(BitmapFactory.decodeResource(
				this.getResources(), R.drawable.ic_launcher));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 将该Activity上的触碰事件交个GestureDetector处理
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
		velocityX = velocityX > 4000 ? 4000 : velocityX;
		velocityX = velocityX < -4000 ? -4000 : velocityX;
		// 根据手势的速度计算缩放比，如果velocityX>0放大图像，否则缩小
		currentScale += currentScale * velocityX / 4000.0f;
		// 保证currentScale不小于0
		currentScale = currentScale > 0.01f ? currentScale : 0.01f;
		// 重置Matrix
		matrix.setScale(currentScale, currentScale, 160, 200);
		BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();
		// 如果图片还未回收，先强制回收该图片
		if (!tmp.getBitmap().isRecycled()) {
			tmp.getBitmap().recycle();
		}
		// 根据原始位图和Matrix创建新图片
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		// 显示新的位图
		imageView.setImageBitmap(bitmap2);
		return true;
	}
}