package com.example.recognisegesture;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	// 定义手势编辑组件
	GestureOverlayView gestureOverlayView;
	// 记录手机上已有的手势库
	GestureLibrary gestureLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 读取上一个应用所创建的手势库
		gestureLibrary = GestureLibraries.fromFile("/mnt/sdcard/mygestures");
		if (gestureLibrary.load()) {
			Toast.makeText(MainActivity.this, "手势库装载成功", 0).show();
		} else {
			Toast.makeText(MainActivity.this, "手势库装载失败", Toast.LENGTH_LONG)
					.show();
		}
		// 获取手势编辑组件
		gestureOverlayView = (GestureOverlayView) findViewById(R.id.gesture);
		gestureOverlayView
				.addOnGesturePerformedListener(new OnGesturePerformedListener() {

					@Override
					public void onGesturePerformed(GestureOverlayView overlay,
							Gesture gesture) {
						// TODO Auto-generated method stub
						// 识别用户刚刚绘制的手势
						ArrayList<Prediction> predictions = gestureLibrary
								.recognize(gesture);
						ArrayList<String> result = new ArrayList<String>();
						// 遍历所有找到的Prediction
						for (Prediction pred : predictions) {
							// 只有相似度大于2.0的手势才会被输出
							if (pred.score > 2.0) {
								result.add("与手势【" + pred.name + "】相识度为"
										+ pred.score);
							}
						}
						if (result.size() > 0) {
							ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
									MainActivity.this,
									android.R.layout.simple_dropdown_item_1line,
									result.toArray());
							// 使用一个带list的对话框显示所有匹配的手势
							new AlertDialog.Builder(MainActivity.this)
									.setAdapter(adapter, null)
									.setPositiveButton("确定", null).show();
						} else {
							Toast.makeText(MainActivity.this, "无法找到能匹配的手势", 0)
									.show();
						}
					}
				});
	}
}