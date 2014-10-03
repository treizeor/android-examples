package com.example.mediaprovidertest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button view = (Button) findViewById(R.id.view);
		Button add = (Button) findViewById(R.id.add);
		final ListView show = (ListView) findViewById(R.id.show);

		final ArrayList<String> names = new ArrayList<String>();
		final ArrayList<String> descs = new ArrayList<String>();
		final ArrayList<String> fileNames = new ArrayList<String>();

		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 清空names、descs、filenames集合里的所有数据
				names.clear();
				descs.clear();
				fileNames.clear();
				// 通过ContentResolver查询所有图片信息
				Cursor cursor = getContentResolver().query(
						Media.EXTERNAL_CONTENT_URI, null, null, null, null);
				while (cursor.moveToNext()) {
					String name = cursor.getString(cursor
							.getColumnIndex(Media.DISPLAY_NAME));
					String desc = cursor.getString(cursor
							.getColumnIndex(Media.DESCRIPTION));
					// 获取图片的保存位置的数据
					byte[] data = cursor.getBlob(cursor
							.getColumnIndex(Media.DATA));

					names.add(name);
					descs.add(desc);
					fileNames.add(new String(data, 0, data.length - 1));
				}
				List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
				// 将names，descs两个集合对象的数据转换到Map集合中
				for (int i = 0; i < names.size(); i++) {
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("name", names.get(i));
					listItem.put("desc", descs.get(i));
					listItems.add(listItem);
				}
				SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
						listItems, R.layout.line,
						new String[] { "name", "desc" }, new int[] { R.id.name,
								R.id.desc });
				show.setAdapter(adapter);
			}
		});

		show.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				View viewDialog = getLayoutInflater().inflate(R.layout.view,
						null);
				ImageView imageView = (ImageView) viewDialog
						.findViewById(R.id.imageView1);
				imageView.setImageBitmap(BitmapFactory.decodeFile(fileNames
						.get(position)));
				new AlertDialog.Builder(MainActivity.this).setView(viewDialog)
						.setPositiveButton("确定", null).show();
			}
		});

		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 创建ContentValues对象，准备插入数据
				ContentValues values = new ContentValues();
				values.put(Media.DISPLAY_NAME, "美女");
				values.put(Media.DESCRIPTION, "美女照片");
				values.put(Media.MIME_TYPE, "image/jpeg");
				// 插入数据，返回所插入数据的Uri
				Uri uri = getContentResolver().insert(
						Media.EXTERNAL_CONTENT_URI, values);
				// 加载应用下的美女图片
				Bitmap bitmap = BitmapFactory.decodeResource(
						MainActivity.this.getResources(), R.drawable.img4);
				OutputStream ops = null;
				try {
					// 获取刚刚插入的数据的uri对应的输出流
					ops = getContentResolver().openOutputStream(uri);
					// 将bitmap保存到Uri对应的节点中
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ops);
					ops.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}