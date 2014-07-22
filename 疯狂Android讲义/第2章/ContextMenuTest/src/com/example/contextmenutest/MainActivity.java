package com.example.contextmenutest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	final int MENU1 = 0x111;
	final int MENU2 = 0x112;
	final int MENU3 = 0x113;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textView1);
		// 为textView注册上下文菜单
		registerForContextMenu(textView);
	}

	// 创建ContextMenu时触发该方法onCreateContextMenu
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// TODO Auto-generated method stub
		menu.add(0, MENU1, 0, "红色");
		menu.add(0, MENU2, 0, "蓝色");
		menu.add(0, MENU3, 0, "绿色");
		// 将三个菜单设置为单选菜单项
		//menu.setGroupCheckable(0, true, true);
		// 设置菜单标题、图标
		menu.setHeaderIcon(R.drawable.ic_launcher);
		menu.setHeaderTitle("选择颜色:");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case MENU1:
			textView.setTextColor(Color.RED);
			break;
		case MENU2:
			textView.setTextColor(Color.BLUE);
			break;
		case MENU3:
			textView.setTextColor(Color.GREEN);
			break;
		}
		return super.onContextItemSelected(item);
	}
}
