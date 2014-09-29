package com.example.dictprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	MyDatabaseHelper dbHelper;
	Button insert;
	Button search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDatabaseHelper(this, "myDict.db3", null, 1);
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
				insertData(dbHelper.getReadableDatabase(), word, detail);
				Toast.makeText(MainActivity.this, "插入生词成功", 0).show();
			}
		});

		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String key = ((EditText) findViewById(R.id.key)).getText()
						.toString();
				Cursor cursor = dbHelper
						.getReadableDatabase()
						.rawQuery(
								"select * from dict where word like ? or detail like ?",
								new String[] { "%" + key + "%", "%" + key + "%" });
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
			Map<String, String> item = new HashMap<String, String>();
			// 取出查询记录中第2,3列的值
			item.put("word", cursor.getString(1));
			item.put("detail", cursor.getString(2));
			result.add(item);
		}
		return result;
	}

	protected void insertData(SQLiteDatabase db, String word, String detail) {
		// TODO Auto-generated method stub
		db.execSQL("insert into dict values(null, ?, ?)", new String[] { word,
				detail });
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}
}