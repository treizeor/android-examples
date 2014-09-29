package com.example.dictresolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	ContentResolver contentResolver;
	Button insert, search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取系统的ContentResolver对象
		contentResolver = getContentResolver();
		insert = (Button) findViewById(R.id.insert);
		search = (Button) findViewById(R.id.search);

		insert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String word = ((EditText) findViewById(R.id.word)).getText()
						.toString();
				String detail = ((EditText) findViewById(R.id.detail))
						.getText().toString();

				// 插入生词记录
				ContentValues values = new ContentValues();
				values.put(Words.Word.WORD, word);
				values.put(Words.Word.DETAIL, detail);
				contentResolver.insert(Words.Word.DICT_CONTENT_URI, values);
				Toast.makeText(MainActivity.this, "添加单词成功", 0).show();
			}
		});

		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String key = ((EditText) findViewById(R.id.key)).getText()
						.toString();
				Cursor cursor = contentResolver.query(
						Words.Word.DICT_CONTENT_URI, null,
						"word like ? or detail like ?", new String[] {
								"%" + key + "%", "%" + key + "%" }, null);
				Bundle data = new Bundle();
				data.putSerializable("data", converCursorToList(cursor));
				Intent intent = new Intent(MainActivity.this,
						ResultActivity.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});

	}

	protected ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
		// TODO Auto-generated method stub
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();
		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put(Words.Word.WORD, cursor.getString(1));
			map.put(Words.Word.DETAIL, cursor.getString(2));
			result.add(map);
		}
		return result;
	}
}