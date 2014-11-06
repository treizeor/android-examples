package com.example.viewhtml;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends Activity {

	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webView1);
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		builder.append("<head>");
		builder.append("<title>欢迎您</title>");
		builder.append("</head>");
		builder.append("<body>");
		builder.append("<h2> 欢迎访问<a href=\"http://treize.cn\">treize.cn</a></h2>");
		builder.append("</body>");
		builder.append("</html>");
		webView.loadDataWithBaseURL(null, builder.toString(), "text/html",
				"utf-8", null);
	}
}