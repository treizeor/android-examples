package com.example.actionbardroddownnav;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity implements OnNavigationListener {
	private static final String SELECTED_ITEM = "selected_item";
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		// 设置导航模式为List
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		actionBar.setListNavigationCallbacks(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				new String[] { "第一页", "第二页", "第三页" }), this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		Fragment fragment = new DummyFragment();
		Bundle args = new Bundle();
		args.putInt(DummyFragment.ARG_SECTION_NUMBER, itemPosition);
		fragment.setArguments(args);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, fragment);
		ft.commit();
		return true;
	}

}
