package com.example.datatypeoverride;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void overrideType(View view) {
		Intent intent = new Intent();
		intent.setType("abc/xyz");
		intent.setData(Uri.parse("chuang://treize.cn:80/test"));
		Toast.makeText(this, intent.toString(), 0).show();
	}
	public void overrideData(View view) {
		Intent intent = new Intent();
		intent.setData(Uri.parse("chuang://treize.cn:80/test"));
		intent.setType("abc/xyz");
		Toast.makeText(this, intent.toString(), 0).show();
		
	}
	public void overrideDataAndType(View view) {
		Intent intent = new Intent();
		intent.setDataAndType(Uri.parse("chueng://treize.cn:90/test"), "abc/xyz");
		Toast.makeText(this, intent.toString(), 0).show();
	}
}