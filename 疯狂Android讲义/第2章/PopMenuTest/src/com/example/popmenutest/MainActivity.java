package com.example.popmenutest;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class MainActivity extends Activity {

	PopupMenu popupMenu;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.button1);
		popupMenu = new PopupMenu(MainActivity.this, button);
		// 将R.menu.main的菜单项加载到popupMenu中
		getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
		
		// popupMenu菜单项单击事件
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "你单击了"+item.getTitle()+"项", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		// button中使用show()方法展示出来
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupMenu.show();
			}
		});
	}
}