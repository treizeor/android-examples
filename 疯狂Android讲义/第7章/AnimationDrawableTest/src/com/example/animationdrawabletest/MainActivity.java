package com.example.animationdrawabletest;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button play = (Button)findViewById(R.id.play);
		Button stop = (Button)findViewById(R.id.stop);
		ImageView imageView = (ImageView)findViewById(R.id.anim);
		//获取AnimationDrawable动画对象 
		final AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
		play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				anim.start();
			}
		});
		
		stop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				anim.stop();
			}
		});
	}
}