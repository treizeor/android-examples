package com.example.togglebuttontest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	ToggleButton toggleButton;
	Switch switch1;
	LinearLayout root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toggleButton = (ToggleButton) findViewById(R.id.toggle);
		switch1 = (Switch)findViewById(R.id.switcher);
		root = (LinearLayout) findViewById(R.id.root);

		OnCheckedChangeListener listener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					root.setOrientation(1);
					toggleButton.setChecked(true);
					switch1.setChecked(true);
				} else {
					root.setOrientation(0);
					toggleButton.setChecked(false);
					switch1.setChecked(false);
				}
			}
		};
		
		toggleButton.setOnCheckedChangeListener(listener);
		switch1.setOnCheckedChangeListener(listener);
		
	}
}