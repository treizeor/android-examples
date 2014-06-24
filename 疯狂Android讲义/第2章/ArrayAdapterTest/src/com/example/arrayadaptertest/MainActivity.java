package com.example.arrayadaptertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ListView list1 = (ListView) findViewById(R.id.list1);
		ListView list2 = (ListView) findViewById(R.id.list2);
		String[] arr1 = { "孙悟空", "猪八戒", "沙悟净", "白龙马" };
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				R.layout.array_item, arr1);
		list1.setAdapter(adapter1);

		String[] arr2 = { "Java", "Golang", "Python" };
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				R.layout.array_item, arr2);
		list2.setAdapter(adapter2);

	}
}