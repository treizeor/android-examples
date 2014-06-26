package com.example.gridviewtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	GridView gridView;
	ImageView imageView;

	int[] imgIds = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.img1, R.drawable.ic_launcher,
			R.drawable.img2, R.drawable.ic_launcher,
			R.drawable.img3, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher, R.drawable.img4, };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imgIds.length; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("img", imgIds[i]);
			items.add(item);
		}

		imageView = (ImageView) findViewById(R.id.imgview);
		gridView = (GridView) findViewById(R.id.grid);

		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, items,
				R.layout.cell, new String[] { "img" },
				new int[] { R.id.img1 });

		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				imageView.setImageResource(imgIds[position]);
				
			}
		});
	}
}