package com.example.spinnertest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Spinner spinner = (Spinner)findViewById(R.id.spinner);
		String[] arr = {"关关雎鸠，在河之洲","窈窕淑女，君子好逑","参差荇菜，左右流之","窈窕淑女，寤寐求之"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arr);
		spinner.setAdapter(adapter);
	}
}