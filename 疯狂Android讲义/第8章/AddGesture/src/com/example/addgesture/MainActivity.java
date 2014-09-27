package com.example.addgesture;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {

	EditText editText;
	GestureOverlayView gestureView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// editText = (EditText) findViewById(R.id.gesture_name);
		// 获取手势编辑视图
		gestureView = (GestureOverlayView) findViewById(R.id.gesture);
		// 设置手势的绘制颜色
		gestureView.setGestureColor(Color.RED);
		// 设置手势绘制宽度
		gestureView.setGestureStrokeWidth(4);
		// 为gestureView完成事件绑定监听器
		gestureView
				.addOnGesturePerformedListener(new OnGesturePerformedListener() {

					@Override
					public void onGesturePerformed(GestureOverlayView overlay,
							final Gesture gesture) {
						// 加载save.xml界面布局代表的视图
						View saveDialog = getLayoutInflater().inflate(
								R.layout.save, null);
						ImageView imageView = (ImageView) saveDialog
								.findViewById(R.id.show);
						final EditText gestureName = (EditText) saveDialog
								.findViewById(R.id.gesture_name);
						// 根据Gesture手势创建一个位图
						Bitmap bitmap = gesture.toBitmap(128, 128, 10,
								0xffff0000);
						imageView.setImageBitmap(bitmap);
						// 使用对话框显示saveDialog组件
						new AlertDialog.Builder(MainActivity.this)
								.setView(saveDialog)
								.setPositiveButton("保存", new OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										// 获取指定文本对应的手势库
										GestureLibrary gestureLibrary = GestureLibraries
												.fromFile("/mnt/sdcard/mygestures");
										// 添加手势
										gestureLibrary.addGesture(gestureName
												.getText().toString(), gesture);
										// 保存手势库
										gestureLibrary.save();
									}
								}).setNegativeButton("取消", null).show();
					}
				});
	}
}