package com.example.configurationtest;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText ori, navigation, touch, mnc;
	Button getinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ori = (EditText) findViewById(R.id.editText1);
		navigation = (EditText) findViewById(R.id.editText2);
		touch = (EditText) findViewById(R.id.editText3);
		mnc = (EditText) findViewById(R.id.editText4);
		getinfo = (Button) findViewById(R.id.button1);

		getinfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取系统的Configuration对象
				Configuration cfg = getResources().getConfiguration();
				String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕"
						: "竖向屏幕";
				String mncCode = cfg.mnc + "";
				String naviName = cfg.orientation == Configuration.NAVIGATION_NONAV ? "没有方向控制"
						: cfg.orientation == Configuration.NAVIGATION_WHEEL ? "滚轮方向控制"
								: cfg.orientation == Configuration.NAVIGATION_DPAD ? "方向键控制方向"
										: "轨迹球控制方向";
				String touchString = cfg.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏"
						: "支持触摸屏";
				ori.setText(screen);
				navigation.setText(naviName);
				touch.setText(touchString);
				mnc.setText(mncCode);

			}
		});
	}
}