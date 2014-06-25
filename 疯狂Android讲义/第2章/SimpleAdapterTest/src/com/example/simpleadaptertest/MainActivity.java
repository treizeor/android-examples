package com.example.simpleadaptertest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String[] names = new String[] { "虎头", "弄玉", "李清照", "李白" };
	private String[] descs = new String[] { "一个可爱的小孩", "一个擅长音乐的女孩",
			"一个擅长文学的女性", "浪漫主义诗人" };
	private int[] imageIds = new int[] { R.drawable.img1, R.drawable.img2,
			R.drawable.img3, R.drawable.img4 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("header", imageIds[i]);
			listItem.put("personName", names[i]);
			listItem.put("desc", descs[i]);
			listItems.add(listItem);
		}

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.simple_item, new String[] { "personName", "header",
						"desc" },
				new int[] { R.id.name, R.id.header, R.id.desc });
		ListView list = (ListView) findViewById(R.id.mylist);
		list.setAdapter(simpleAdapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, String.valueOf(names[position]), Toast.LENGTH_LONG).show();
			}
		});
		list.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, names[position]+" 被选中了", 0).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}