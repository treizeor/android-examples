package com.example.toasttext;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button button1, button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button)findViewById(R.id.button1);
		button2	= (Button)findViewById(R.id.button2);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(MainActivity.this, "简单提示", Toast.LENGTH_LONG);
				toast.show();
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = new Toast(MainActivity.this);
				toast.setGravity(Gravity.CENTER, 0, 0);
				ImageView img = new ImageView(MainActivity.this);
				img.setImageResource(R.drawable.ic_launcher);
				//创建一个LinearLayout容器
				LinearLayout ll = new LinearLayout(MainActivity.this);
				ll.addView(img);
				
				TextView text = new TextView(MainActivity.this);
				text.setText("带图片信息的提示");
				text.setTextSize(30);
				text.setTextColor(Color.MAGENTA);
				ll.addView(text);
				
				//设置Toast显示自定义的View
				toast.setView(ll);
				
				//设置Toast显示时间
				toast.setDuration(Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}
}