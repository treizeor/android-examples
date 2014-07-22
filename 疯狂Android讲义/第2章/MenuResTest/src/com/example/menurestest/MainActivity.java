package com.example.menurestest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 textView = (TextView)findViewById(R.id.textView1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.font_10:
			textView.setTextSize(10 * 2);
			break;
		case R.id.font_12:
			textView.setTextSize(12 * 2);
			break;
		case R.id.font_14:
			textView.setTextSize(14 * 2);
			break;
		case R.id.font_16:
			textView.setTextSize(16 * 2);
			break;
		case R.id.font_18:
			textView.setTextSize(18 * 2);
			break;
		case R.id.font_red:
			textView.setTextColor(Color.RED);
			break;
		case R.id.font_blue:
			textView.setTextColor(Color.BLUE);
			break;
		case R.id.font_green:
			textView.setTextColor(Color.GREEN);
			break;

		}
		return super.onOptionsItemSelected(item);
	}

}
