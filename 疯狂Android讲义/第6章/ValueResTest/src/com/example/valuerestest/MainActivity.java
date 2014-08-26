package com.example.valuerestest;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends Activity {

	int[] textIDs = new int[] { R.string.c1, R.string.c2, R.string.c3,
			R.string.c4, R.string.c5, R.string.c6, R.string.c7, R.string.c8,
			R.string.c9, };

	int[] colorsIDs = new int[] { R.color.c1, R.color.c2, R.color.c3,
			R.color.c4, R.color.c5, R.color.c6, R.color.c7, R.color.c8,
			R.color.c9, };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BaseAdapter adapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView tv = new TextView(MainActivity.this);
				Resources res = MainActivity.this.getResources();
				tv.setWidth((int) res.getDimension(R.dimen.cell_width));
				tv.setHeight((int) res.getDimension(R.dimen.cell_height));
				tv.setText(textIDs[position]);
				tv.setBackgroundResource(colorsIDs[position]);
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
				return getResources().getText(textIDs[position]);
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return textIDs.length;
			}
		};
		GridView gridView = (GridView)findViewById(R.id.grid1);
		gridView.setAdapter(adapter);
	}
}