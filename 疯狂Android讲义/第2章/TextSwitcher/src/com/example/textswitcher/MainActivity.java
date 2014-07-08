package com.example.textswitcher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	TextSwitcher switcher;
	String[] strings = new String[] { "枯藤老树昏鸦", "小桥流水人家", "古道西风瘦马", "夕阳西下",
			"断肠人在天涯" };
	int curStr = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		switcher = (TextSwitcher) findViewById(R.id.switcher);
		switcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				TextView tv = new TextView(MainActivity.this);
				tv.setTextSize(40);
				tv.setGravity(Gravity.CENTER_HORIZONTAL);
				tv.setTextColor(Color.MAGENTA);
				return tv;
			}
		});
		next(null);
	}

	public void next(View view) {
		switcher.setText(strings[(curStr++) % strings.length]);
	}
}