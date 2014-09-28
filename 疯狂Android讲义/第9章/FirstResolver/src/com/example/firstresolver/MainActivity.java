package com.example.firstresolver;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	ContentResolver resolver;
	Uri uri = Uri.parse("content://com.example.firstprovider/");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取系统的ContentResolver对象
		resolver = getContentResolver();
	}

	public void query(View view) {
		// 调用ContentResolver的query()方法
		// 实际返回的是该Uri对应的ContentProvider的query()的返回值
		Cursor c = resolver.query(uri, null, "query_selection", null, null);
		Toast.makeText(this, "远程ContentProvider返回的Cursor为:" + c, 0).show();
	}

	public void insert(View view) {
		ContentValues values = new ContentValues();
		values.put("name", "TREIZE");
		// 调用ContentResolver的insert()方法
		// 实际返回的是该Uri对应的ContentProvider的insert()的返回值
		Uri newUri = resolver.insert(uri, values);
		Toast.makeText(this, "远程ContentProvider插入记录的Uri为:" + newUri, 0).show();
	}
	
	public void update(View view) {
		ContentValues values = new ContentValues();
		values.put("name", "treize");
		int count = resolver.update(uri, values, "update_where", null);
		Toast.makeText(this, "远程ContentProvider更新记录数为:" + count, 0).show();
		
	}
	
	public void delete(View view) {
		int count = resolver.delete(uri, "delete_where", null);
		Toast.makeText(this, "远程ContentProvider删除记录数为:" + count, 0).show();
		
	}
}