package com.example.quickcontactbadgetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.QuickContactBadge;

public class MainActivity extends Activity {

	QuickContactBadge badge;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		badge = (QuickContactBadge) findViewById(R.id.badge);
		badge.assignContactFromPhone("12345", false);
	}
}