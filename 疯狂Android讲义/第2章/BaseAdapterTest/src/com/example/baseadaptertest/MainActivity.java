package com.example.baseadaptertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView list = (ListView) findViewById(R.id.mylist);
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LinearLayout line = new LinearLayout(MainActivity.this);
				line.setOrientation(0);
				ImageView image = new ImageView(MainActivity.this);
				image.setImageResource(R.drawable.ic_launcher);
				TextView text = new TextView(MainActivity.this);
				text.setText("第" + (position + 1) + "个列表项");
				line.addView(image);
				line.addView(text);

				return line;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 50;
			}
		};
		list.setAdapter(adapter);
	}
}