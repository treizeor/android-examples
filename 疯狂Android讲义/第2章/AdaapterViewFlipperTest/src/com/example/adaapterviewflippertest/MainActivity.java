package com.example.adaapterviewflippertest;

import java.security.PublicKey;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private int[] imgIds = new int[] { R.drawable.img1, R.drawable.img2,
			R.drawable.img3, R.drawable.img4, };

	private AdapterViewFlipper flipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		flipper = (AdapterViewFlipper) findViewById(R.id.flipper);
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ImageView imageView = new ImageView(MainActivity.this);
				imageView.setImageResource(imgIds[position]);
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
				return imageView;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return imgIds.length;
			}
		};
		flipper.setAdapter(adapter);
	}
	public void prev(View view){
		flipper.showPrevious();
		flipper.stopFlipping();
	}
	public void next(View view){
		flipper.showNext();
		flipper.stopFlipping();
	}
	
	public void auto(View view){
		flipper.startFlipping();
	}
}