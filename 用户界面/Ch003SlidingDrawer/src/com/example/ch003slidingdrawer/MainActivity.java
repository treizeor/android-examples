package com.example.ch003slidingdrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ImageView img;
	private SlidingDrawer sliding;   //SlidingDrawer在新的SDK中已弃用
	private TextView txt;
	

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img = (ImageView) findViewById(R.id.handle);
		sliding = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		txt = (TextView) findViewById(R.id.txt);
		
		sliding.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				img.setImageResource(R.drawable.img2);
			}
		});
		
		sliding.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				img.setImageResource(R.drawable.img4);
				
			}
		});
		
		sliding.setOnDrawerScrollListener(new OnDrawerScrollListener() {
			
			@Override
			public void onScrollStarted() {
				// TODO Auto-generated method stub
				txt.setText("滚动开始");
			}
			
			@Override
			public void onScrollEnded() {
				// TODO Auto-generated method stub
				txt.setText("滚动结束");
			}
		});
		
	}

}
