package com.example.datatypeattr;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button s, shpa, shpo, shpp, shppt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		s = (Button)findViewById(R.id.button1);
		shpa = (Button)findViewById(R.id.button2);
		shpo = (Button)findViewById(R.id.button3);
		shpp = (Button)findViewById(R.id.button4);
		shppt = (Button)findViewById(R.id.button5);
		
		s.setOnClickListener(this);
		shpa.setOnClickListener(this);
		shpo.setOnClickListener(this);
		shpp.setOnClickListener(this);
		shppt.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.button1:
			intent.setData(Uri.parse("chueng://www.treize.cn:1235/test"));
			break;
		case R.id.button2:
			intent.setData(Uri.parse("chueng://www.treize.cn:1235/mypath"));
			
			break;
		case R.id.button3:
			intent.setData(Uri.parse("chueng://www.treize.cn:8888/my1path"));
			
			break;
		case R.id.button4:
			intent.setData(Uri.parse("chueng://www.treize.cn:8888/mypath"));
			
			break;
		case R.id.button5:
			intent.setDataAndType(Uri.parse("chueng://www.treize.cn:8888/mypath"), "abc/xyz");
			
			break;
		}
		startActivity(intent);
	}

}