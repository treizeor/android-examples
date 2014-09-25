package com.example.sdfileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView listView;
	TextView textView;
	// 记录当前的父目录
	File currentParent;
	// 记录当前路径下的所有文件的文件数组
	File[] currentFiles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.list);
		textView = (TextView) findViewById(R.id.path);
		// 获取SD卡目录
		File root = new File("/mnt/sdcard/");
		// 如果SD卡存在
		if (root.exists()) {
			currentParent = root;
			currentFiles = root.listFiles();
			// 使用当前目录下的全部文件和文件夹填充ListView
			inflateListView(currentFiles);
		}
		// 为ListView的列表项的单击事件绑定监听器
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// 用户单击了文件，直接返回，不做任何处理
				if (currentFiles[position].isFile()) {
					return;
				}
				// 获取用户点击的文件夹下面的所有文件
				File[] tmp = currentFiles[position].listFiles();
				if (tmp == null || tmp.length == 0) {
					Toast.makeText(MainActivity.this, "当前路径不可访问或该路径下没有文件", 0).show();
				}
				else {
					// 获取用户点击列表项的文件夹，设为当前路径
					currentParent = currentFiles[position];
					currentFiles = tmp;
					// 再次更新ListView
					inflateListView(currentFiles);
				}
			}
		});
		
		Button parent = (Button)findViewById(R.id.parent);
		parent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (!currentParent.getCanonicalPath().equals("/mnt/sdcard/")) {
						// 获取上一级目录
						currentParent = currentParent.getParentFile();
						// 列出当前目录下所有文件
						currentFiles = currentParent.listFiles();
						// 更新ListView
						inflateListView(currentFiles);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}

	private void inflateListView(File[] files) {
		// TODO Auto-generated method stub
		// 创建一个List集合，List集合的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < files.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			// 如果当前File是文件夹，使用folder图标;否则使用file图标
			if (files[i].isDirectory()) {
				listItem.put("icon", R.drawable.folder);
			} else {
				listItem.put("icon", R.drawable.file);
			}
			listItem.put("fileName", files[i].getName());
			listItems.add(listItem);
		}
		// 创建一个SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.line, new String[] { "icon", "fileName" }, new int[] {
						R.id.icon, R.id.fileName });
		// 为listView设置adapter
		listView.setAdapter(simpleAdapter);
		try {
			textView.setText("当前路径为:"+currentParent.getCanonicalPath());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}