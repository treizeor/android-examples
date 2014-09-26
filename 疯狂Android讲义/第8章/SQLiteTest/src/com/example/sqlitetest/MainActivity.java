package com.example.sqlitetest;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	SQLiteDatabase db;
	Button insert;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 打开或创建数据库，此处需要绝对路径
		db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
				+ "/my.db3", null);
		listView = (ListView) findViewById(R.id.listView1);
		insert = (Button) findViewById(R.id.button1);
		insert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 获取用户输入
				String title = ((EditText) findViewById(R.id.editText1))
						.getText().toString();
				String content = ((EditText) findViewById(R.id.editText2))
						.getText().toString();
				try {
					insertData(db, title, content);
					Cursor cursor = db.rawQuery("select * from news_inf", null);
					inflateList(cursor);
				} catch (SQLiteException se) {
					// TODO: handle exception
					db.execSQL("create table news_inf("
							+ "_id integer primary key autoincrement, "
							+ "news_title varchar(50), "
							+ "news_content varchar(255))");
					insertData(db, title, content);
					Cursor cursor = db.rawQuery("select * from news_inf", null);
					inflateList(cursor);
				}

			}
		});
	}

	private void insertData(SQLiteDatabase db, String title, String content) {
		// 执行插入语句
		db.execSQL("insert into news_inf values(null, ?, ?)", new String[] {
				title, content });
	}

	private void inflateList(Cursor cursor) {
		// 填充SimpleCursorAdapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				MainActivity.this, R.layout.line, cursor, new String[] {
						"news_title", "news_content" }, new int[] {
						R.id.my_title, R.id.my_content },
				CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (db != null && db.isOpen()) {
			db.close();
		}
	}

}