package com.example.actionbartabnav;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements TabListener {

	private final static String SELECTED_ITEM = "selected_item";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getActionBar();
        // 设置导航方式NAVIGATION_MODE_TABS
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // 添加三个TAB，并添加时间监听器
        actionBar.addTab(actionBar.newTab().setText("TAB1").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("TAB2").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText("TAB3").setTabListener(this));

      
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
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Fragment fragment = new DummyFragment();
		// 创建一个Bundle对象，用于向Fragment传递参数
		Bundle args = new Bundle();
		args.putString(DummyFragment.ARG_SECTION_NUMBER, tab.getText()+"");
		// 向fragment传入参数
		fragment.setArguments(args);
		// 获取FragmentTransaction对象
		ft = getFragmentManager().beginTransaction();
		//使用Fragment代替该Activity中的container组件
		ft.replace(R.id.container, fragment);
		// 提交事务
		ft.commit();
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	
	
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (savedInstanceState.containsKey(SELECTED_ITEM)) {
			getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(SELECTED_ITEM));
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	
	}

}
