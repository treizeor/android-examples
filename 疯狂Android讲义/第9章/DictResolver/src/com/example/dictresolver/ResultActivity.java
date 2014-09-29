package com.example.dictresolver;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
		ListView listView = (ListView) findViewById(R.id.show);
		Intent intent = getIntent();
		Bundle data = intent.getExtras();
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = (List<Map<String, String>>) data
				.getSerializable("data");
		SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this, list,
				R.layout.line, new String[] { Words.Word.WORD,
						Words.Word.DETAIL },
				new int[] { R.id.word, R.id.detail });
		listView.setAdapter(adapter);

	}
}
