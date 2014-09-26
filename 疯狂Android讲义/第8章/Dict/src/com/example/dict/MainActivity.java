package com.example.dict;

import java.io.Serializable;
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
	Button insert = null;
	Button search = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 创建MyDatabaseHelper对象,指定数据库版本为1，此处使用相对路径即可
		// 数据库文件会自动保存在程序的数据文件夹databases目录下
		dbHelper = new MyDatabaseHelper(this, "myDict.db3", null, 1);
		insert = (Button) findViewById(R.id.insert);
		search = (Button) findViewById(R.id.search);
		insert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 获取用户输入
				String word = ((EditText) findViewById(R.id.word)).getText()
						.toString();
				String detail = ((EditText) findViewById(R.id.detail))
						.getText().toString();
				// 插入生成记录
				insertData(dbHelper.getReadableDatabase(), word, detail);
				Toast.makeText(MainActivity.this, "添加生词成功!", 0).show();
			}
		});
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String key = ((EditText) findViewById(R.id.key)).getText()
						.toString();
				// 执行查询
				Cursor cursor = dbHelper
						.getReadableDatabase()
						.rawQuery(
								"select * from dict where word like ? or detail like ?",
								new String[] { "%" + key + "%", "%" + key + "%" });
				// 创建一个Bundle对象
				Bundle data = new Bundle();
				data.putSerializable("data", converCursorToList(cursor));
				Intent intent = new Intent(MainActivity.this, ResultActivity.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});
	}

	protected ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
		// TODO Auto-generated method stub
		ArrayList<Map<String, String>> result = new ArrayList<Map<String,String>>();
		// 遍历cursor结果集
		while (cursor.moveToNext()) {
			// 将结果集中的数据存入ArrayList中
			Map<String, String> map = new HashMap<String, String>();
			map.put("word", cursor.getString(1));
			map.put("detail", cursor.getString(2));
			result.add(map);
		}
		return result;
	}

	protected void insertData(SQLiteDatabase db, String word, String detail) {
		// 执行插入语句
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