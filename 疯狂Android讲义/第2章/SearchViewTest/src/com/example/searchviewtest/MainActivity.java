package com.example.searchviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	SearchView searchView;
	ListView listView;
	// 自动完成列表
	private final String[] mStrings = { "aaaa", "abc", "addb", "bbbb", "cccc" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, mStrings));
		listView.setTextFilterEnabled(true);

		searchView = (SearchView) findViewById(R.id.searchView);
		// 设置默认是否自动缩小为图标
		searchView.setIconifiedByDefault(true);
		// 设置该SearchView显示搜索按钮
		searchView.setSubmitButtonEnabled(true);
		// 提示文本
		searchView.setQueryHint("查找");
		// 设置监听事件
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"Your query string is:" + query, 0).show();
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				if (TextUtils.isEmpty(newText)) {
					// 清除listview的过滤
					listView.clearTextFilter();
				} else {
					// 使用用户输入的内容对ListView的列表项进行过滤
					listView.setFilterText(newText);
				}
				return true;
			}
		});
	}
}