package com.example.jscallandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

	WebView webView;

	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView1);
		// 加载html，此处为了简化，使用file协议加载本地html，也可使用http协议加载远程html
		webView.loadUrl("file:///android_asset/test.html");
		// 获取webView的设置对象
		WebSettings webSettings = webView.getSettings();
		// 开启JavaScript调用
		webSettings.setJavaScriptEnabled(true);
		// 将MyObject对象暴露给JavaScript脚本
		// 这样test.html中的JavaScript可以通过myObj来调用MyObject的方法
		webView.addJavascriptInterface(new MyObject(this), "myObj");
	}
}