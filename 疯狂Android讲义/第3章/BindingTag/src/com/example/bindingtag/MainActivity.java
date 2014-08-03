package com.example.bindingtag;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void BindButton(View view) {
		Toast.makeText(MainActivity.this, "绑定到标签的事件监听器", 0).show();
	}
}