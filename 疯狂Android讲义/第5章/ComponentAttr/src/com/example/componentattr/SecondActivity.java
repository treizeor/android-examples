package com.example.componentattr;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(SecondActivity.this);
		layout.setOrientation(LinearLayout.VERTICAL);
		EditText text = new EditText(SecondActivity.this);
		layout.addView(text);
		setContentView(layout);
		ComponentName comp = getIntent().getComponent();
		text.setText("组件名称为:" + comp.getPackageName() + "\n组件类名为:"
				+ comp.getClassName());
		
	}
}
