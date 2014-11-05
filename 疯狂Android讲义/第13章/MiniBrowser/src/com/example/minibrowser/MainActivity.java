package com.example.minibrowser;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText url;
	WebView show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		url = (EditText) findViewById(R.id.url);
		show = (WebView) findViewById(R.id.show);
	}

	public void submit(View v) {
		String urlStr = url.getText().toString();
		show.loadUrl(urlStr);
	}
}