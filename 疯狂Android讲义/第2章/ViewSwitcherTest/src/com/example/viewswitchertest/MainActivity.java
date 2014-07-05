package com.example.viewswitchertest;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	// 定义一个常量，用于显示每屏的app数量
	public static final int NUMBER_PER_SCREEN = 12;

	// 代表app的内部类
	public static class AppItem {
		public String appName;
		public Drawable drawable;
	}

	// 保存系统所有app的List集合
	private ArrayList<AppItem> items = new ArrayList<AppItem>();
	// 记录正在显示第几屏的apps
	private int screenNo = -1;
	// 保存apps所占的总屏数
	private int screenCount;

	ViewSwitcher viewSwitcher;
	// 创建LayoutInflater对象
	LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inflater = LayoutInflater.from(MainActivity.this);

		// 创建一个包含40个元素的List集合，模拟40个app
		for (int i = 0; i < 40; i++) {
			String label = "" + i;
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_launcher);
			AppItem item = new AppItem();
			item.appName = label;
			item.drawable = drawable;
			items.add(item);
		}
		screenCount = items.size() % NUMBER_PER_SCREEN == 0 ? items.size()
				/ NUMBER_PER_SCREEN : items.size() / NUMBER_PER_SCREEN + 1;

		viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
		viewSwitcher.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				return inflater.inflate(R.layout.slidelistview, null);
			}
		});
		// 页面加载时先显示第一屏
	}

	public void prev(View view) {
		if (screenNo > 0) {
			screenNo--;
			viewSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
			viewSwitcher.setInAnimation(this, android.R.anim.slide_out_right);
			((GridView) viewSwitcher.getNextView()).setAdapter(adapter);
			viewSwitcher.showPrevious();
		}
	}

	public void next(View view) {
		if (screenNo < -1) {
			screenNo++;
			viewSwitcher.setInAnimation(this, R.anim.slide_in_right);
			viewSwitcher.setInAnimation(this, R.anim.slide_out_left);
			((GridView) viewSwitcher.getNextView()).setAdapter(adapter);
			viewSwitcher.showNext();
		}
	}

	private BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = convertView;
			if (convertView == null) {
				view = inflater.inflate(R.layout.labelicon, null);
			}
			ImageView imageView = (ImageView) findViewById(R.id.imageview);
			imageView.setImageDrawable((getItem(position)).drawable);
			TextView textView = (TextView)findViewById(R.id.textview);
			textView.setText(getItem(position).appName);

			return view;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public AppItem getItem(int position) {
			// TODO Auto-generated method stub
			return items.get(screenNo * NUMBER_PER_SCREEN + position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (screenNo == screenCount - 1
					&& items.size() % NUMBER_PER_SCREEN != 0) {
				return items.size() % NUMBER_PER_SCREEN;
			}
			return NUMBER_PER_SCREEN;
		}
	};
}