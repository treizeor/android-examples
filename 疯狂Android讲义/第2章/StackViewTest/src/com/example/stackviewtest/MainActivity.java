package com.example.stackviewtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.StackView;

public class MainActivity extends Activity {

	private int[] imgIds = new int[] { R.drawable.img1, R.drawable.img2,
			R.drawable.img3, R.drawable.img4, };
	private StackView stackView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		stackView = (StackView) findViewById(R.id.stackView);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imgIds.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", imgIds[i]);
			listItems.add(listItem);
		}

		SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, listItems,
				R.layout.cell, new String[] { "image" },
				new int[] { R.id.imageView1 });
		stackView.setAdapter(adapter);

	}

	public void prev(View view) {
		stackView.showPrevious();
	}

	public void next(View view) {
		stackView.showNext();
	}
}