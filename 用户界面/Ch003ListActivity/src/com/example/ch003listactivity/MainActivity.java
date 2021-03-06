package com.example.ch003listactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取数据源
		final String[] items = getResources().getStringArray(R.array.items);
		
		// 获取ListView组件
		ListView list = getListView();
		
		// 设置适配器，将系统自带的布局simple_expandable_list_item_1与数据源绑定
		// 并将此布局设为ListView每一行记录的布局
		setListAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_expandable_list_item_1, items));
		
		// 注册单击事件
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, items[position], 0).show();
			}
		});

	}

}
