package com.example.actionattr;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryAttrActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(CategoryAttrActivity.this);
		TextView txt = new TextView(this);
		Set<String> cates = getIntent().getCategories();
		txt.setText("Categorys为:" + cates);
		layout.addView(txt);
		setContentView(layout);
	}
}
