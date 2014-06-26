package com.example.autocompletetextviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends Activity {

	AutoCompleteTextView autotv;
	MultiAutoCompleteTextView mautotv;

	String[] books = new String[] { "a疯狂Java讲义", "a疯狂XML讲义", "a疯狂Ajax讲义",
			"a疯狂Android讲义", };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, books);

		autotv = (AutoCompleteTextView) findViewById(R.id.auto);
		mautotv = (MultiAutoCompleteTextView) findViewById(R.id.mauto);

		autotv.setAdapter(arrAdapter);
		mautotv.setAdapter(arrAdapter);
		mautotv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

	}
}