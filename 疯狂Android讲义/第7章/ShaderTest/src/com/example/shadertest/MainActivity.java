package com.example.shadertest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	// 声明位图渲染对象
	private Shader[] shaders = new Shader[5];
	// 声明颜色数组
	private int[] colors;
	MyView myView;

	// 自定义视图
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myView = (MyView) findViewById(R.id.myView);
		// 获得Bitmap实例
		Bitmap bm = BitmapFactory.decodeResource(getResources(),
				R.drawable.img4);
		// 设置渐变的颜色数组, 按红蓝绿渐变
		colors = new int[] { Color.RED, Color.BLUE, Color.GREEN, };
		// 实例化BitmapShader，x坐标方向重复图像， y方向镜像图形
		shaders[0] = new BitmapShader(bm, TileMode.REPEAT, TileMode.MIRROR);
		// 实例化LinearGradient
		shaders[1] = new LinearGradient(0, 0, 100, 100, colors, null,
				TileMode.REPEAT);
		// 实例化RadiaGradient
		shaders[2] = new RadialGradient(100, 100, 80, colors, null,
				TileMode.REPEAT);
		// 实例化SweepGradient
		shaders[3] = new SweepGradient(160, 160, colors, null);
		// 实例化ComposeShader
		shaders[4] = new ComposeShader(shaders[1], shaders[2],
				PorterDuff.Mode.DARKEN);
		Button btn1 = (Button)findViewById(R.id.btn1);
		Button btn2 = (Button)findViewById(R.id.btn2);
		Button btn3 = (Button)findViewById(R.id.btn3);
		Button btn4 = (Button)findViewById(R.id.btn4);
		Button btn5 = (Button)findViewById(R.id.btn5);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn1:
			myView.paint.setShader(shaders[0]);
			break;
		case R.id.btn2:
			myView.paint.setShader(shaders[1]);
			break;
		case R.id.btn3:
			myView.paint.setShader(shaders[2]);
			break;
		case R.id.btn4:
			myView.paint.setShader(shaders[3]);
			break;
		case R.id.btn5:
			myView.paint.setShader(shaders[4]);
			break;
		}
		myView.invalidate();
	}
}