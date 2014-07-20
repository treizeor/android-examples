package com.example.menutest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;

public class MainActivity extends Activity {
	//定义字体大小菜单项的标识
	final int FONT_10 = 0x111;
	final int FONT_12 = 0x112;
	final int FONT_14 = 0x113;
	final int FONT_16 = 0x114;
	final int FONT_18 = 0x115;
	//定义普通菜单项的标识
	final int PLAIN_ITEM = 0x11b;
	//定义字体颜色菜单项的标识
	final int FONT_RED = 0x116;
	final int FONT_BLUE = 0x117;
	final int FONT_GREEN = 0x118;
	//用于展示效果的EditText
	EditText editText;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText)findViewById(R.id.editText1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		SubMenu fontMenu = menu.addSubMenu("字体大小");
		//设置菜单的图标，在新的系统中没展示出来
		fontMenu.setIcon(R.drawable.ic_launcher);
		fontMenu.setHeaderIcon(R.drawable.ic_launcher);
		fontMenu.setHeaderTitle("菜单头标题");
		fontMenu.addSubMenu(0, FONT_10, 0, "10号字体");
		fontMenu.addSubMenu(0, FONT_12, 0, "12号字体");
		fontMenu.addSubMenu(0, FONT_14, 0, "14号字体");
		fontMenu.addSubMenu(0, FONT_16, 0, "16号字体");
		fontMenu.addSubMenu(0, FONT_18, 0, "18号字体");
		// 向menu中添加普通菜单项
		menu.add(0, PLAIN_ITEM, 0, "普通菜单项");
		// 添加文字颜色的子菜单
		SubMenu colorMenu = menu.addSubMenu("字体颜色");
		colorMenu.add(0, FONT_RED, 0, "红色");
		colorMenu.add(0, FONT_BLUE,0 , "蓝色");
		colorMenu.add(0, FONT_GREEN,0 , "绿色");
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case FONT_10:
			editText.setTextSize(10 * 2);
			break;
		case FONT_12:
			editText.setTextSize(12 * 2);
			break;
		case FONT_14:
			editText.setTextSize(14 * 2);
			break;
		case FONT_16:
			editText.setTextSize(16 * 2);
			break;
		case FONT_18:
			editText.setTextSize(18 * 2);
			break;
		case FONT_RED:
			editText.setTextColor(Color.RED);
			break;
		case FONT_BLUE:
			editText.setTextColor(Color.BLUE);
			break;
		case FONT_GREEN:
			editText.setTextColor(Color.GREEN);
			break;
		case PLAIN_ITEM:
			editText.setText("你单击了普通菜单项");
			break;
		}
		return super.onOptionsItemSelected(item);
	}


}
