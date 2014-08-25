package com.example.intenttab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost = getTabHost();
		tabHost.addTab(tabHost
				.newTabSpec("tab1")
				.setIndicator("已接电话",
						getResources().getDrawable(R.drawable.ic_launcher))
				.setContent(new Intent(MainActivity.this, OneActivity.class)));
		tabHost.addTab(tabHost
				.newTabSpec("tab2")
				.setIndicator("未接电话",
						getResources().getDrawable(R.drawable.ic_launcher))
						.setContent(new Intent(MainActivity.this, TwoActivity.class)));
		tabHost.addTab(tabHost
				.newTabSpec("tab3")
				.setIndicator("呼出电话",
						getResources().getDrawable(R.drawable.ic_launcher))
						.setContent(new Intent(MainActivity.this, ThirdActivity.class)));
	}
}