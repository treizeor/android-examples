package com.example.arrayrestest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {

	String[] texts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		texts = getResources().getStringArray(R.array.string_arr);
		BaseAdapter adapter = new BaseAdapter() {

			@SuppressLint("NewApi") @Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView tv = new TextView(MainActivity.this);
				Resources res = MainActivity.this.getResources();
				tv.setWidth((int) res.getDimension(R.dimen.cell_width));
				tv.setHeight((int) res.getDimension(R.dimen.cell_height));
				tv.setText(texts[position]);
				
				// 颜色资源
				TypedArray icons = res.obtainTypedArray(R.array.plain_arr);
				tv.setBackground(icons.getDrawable(position));
				tv.setTextSize(20);
				return tv;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return texts[position];
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return texts.length;
			}
		};
		GridView gridView = (GridView)findViewById(R.id.grid1);
		gridView.setAdapter(adapter);
	}
}