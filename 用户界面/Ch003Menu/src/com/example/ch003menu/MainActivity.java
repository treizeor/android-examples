package com.example.ch003menu;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 菜单示例
 */

public class MainActivity extends Activity {
	//定义菜单标识
	private static final int MENU_OPTION=0;
	private static final int MENU_HELP=1;
	private static final int MENU_ABOUT=2;
	private static final int MENU_QUIT=3;
	private static final int SUB_MENU_ADD=101;
	private static final int SUB_MENU_DEL=102;
	
	private static final int CONTEXT_MENU_ADD=201;
	private static final int CONTEXT_MENU_DEL=202;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// 使用XML资源文件
		getMenuInflater().inflate(R.menu.main, menu);
		
		// 创建3个普通菜单
		menu.addSubMenu(0, MENU_HELP, 1, "帮助");
		menu.addSubMenu(0, MENU_ABOUT, 2, "关于").setIcon(R.drawable.img2); // 4.x 中菜单图标不可见
		menu.addSubMenu(0, MENU_QUIT, 3, "退出");
		
		// 创建一个包含子菜单的菜单，其中包含“添加”“删除”两个子菜单
		SubMenu subMenu = menu.addSubMenu(0, MENU_OPTION, 0, "操作");
		subMenu.add(0, SUB_MENU_ADD, 1, "添加");
		subMenu.add(0, SUB_MENU_DEL, 2, "删除");
		
		// 放回true，菜单可见，否则不可见
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		
		switch (item.getItemId()) {
		
			// Id为资源文件Id
		case R.id.action_settings:
			Toast.makeText(this, item.toString(), 0).show();
			break;
			
		case R.id.action_add:
			Toast.makeText(this, item.toString(), 0).show();
			break;
			
			// Id为自定义的菜单标识
		case MENU_OPTION:
			Toast.makeText(this, item.toString(), 0).show();
			
			break;
		case MENU_QUIT:
			finish();
			break;
			
	
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		// 创建上下文菜单
		menu.add(0, CONTEXT_MENU_ADD, 1, "添加");
		menu.add(0, CONTEXT_MENU_DEL, 2, "删除");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case CONTEXT_MENU_ADD:
			Toast.makeText(this, item.toString(), 0).show();
			break;
		case CONTEXT_MENU_DEL:
			Toast.makeText(this, item.toString(), 0).show();
			
			break;

		}
		return super.onContextItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			TextView txt = (TextView)rootView.findViewById(R.id.txt);
			// 对TextView组件注册ContextMenu
			registerForContextMenu(txt);
			return rootView;
		}
	}

}
