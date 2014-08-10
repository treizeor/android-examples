package com.example.changecfg;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bn = (Button) findViewById(R.id.button1);
		bn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Configuration cfg = getResources().getConfiguration();
				if (cfg.orientation == Configuration.ORIENTATION_LANDSCAPE) {
					MainActivity.this
							.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				if (cfg.orientation == Configuration.ORIENTATION_PORTRAIT) {
					MainActivity.this
							.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}
			}
		});
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		String screen = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕"
				: "竖向屏幕";
		System.out.println("屏幕方向改变为" + screen);
	}
}