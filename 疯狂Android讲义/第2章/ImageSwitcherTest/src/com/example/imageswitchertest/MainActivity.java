package com.example.imageswitchertest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	int[] imgIds = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.img1,
			R.drawable.ic_launcher, R.drawable.img2, R.drawable.img3,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.img4, };
	ImageSwitcher switcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imgIds.length; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("image", imgIds[i]);
			listItems.add(item);
		}
		switcher = (ImageSwitcher) findViewById(R.id.switcher);
		// 为ImageSwitcher设置图片切换动画效果
		switcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// 创建ImageView对象
				ImageView imageView = new ImageView(MainActivity.this);
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				return imageView;
			}
		});

		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, listItems,
				R.layout.cell, new String[] { "image" },
				new int[] { R.id.img1 });
		GridView grid = (GridView) findViewById(R.id.grid01);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switcher.setImageResource(imgIds[position]);
			}
		});
	}
}