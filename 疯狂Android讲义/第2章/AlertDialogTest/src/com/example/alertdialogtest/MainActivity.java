package com.example.alertdialogtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AliasActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void simple(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
				.setTitle("简单对话框").setIcon(R.drawable.ic_launcher)
				.setMessage("简单对话框测试内容\n换行测试");
		// 添加确定按钮
		setPositiveButton(builder);
		// 添加取消按钮
		setNegativeButton(builder);
		// 创建及展示
		builder.create().show();
	}

	public void simpleList(View view) {
		final String[] items = new String[] { "aaaa", "bbbb", "cccc" };
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
				.setTitle("简单列表对话框").setIcon(R.drawable.ic_launcher)
				.setItems(items, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						show("你选择了" + items[which]);
					}
				});
		setPositiveButton(builder);
		setNegativeButton(builder);
		builder.create().show();
	}

	public void singleChoice(View view) {
		final String[] items = new String[] { "aaa", "bb", "cccc", "dd" };
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
				.setTitle("单选列表项对话框").setIcon(R.drawable.ic_launcher)
				.setSingleChoiceItems(items, 1, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						show("你选择了" + items[which]);
					}
				});
		setPositiveButton(builder);
		setNegativeButton(builder).create().show();
	}

	public void multiChoice(View view) {
		final String[] items = new String[] { "aaa", "ddd", "iii", "kkk",
				"iili" };
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
				.setTitle("多选列表项对话框").setIcon(R.drawable.ic_launcher);
		builder.setMultiChoiceItems(items, new boolean[] { false, true, true,
				false, true }, null);
		setPositiveButton(builder);
		setNegativeButton(builder).create().show();
	}

	public void customList(View view) {
		final String[] items = new String[] { "aaa", "ddd", "iii", "kkk",
				"iili" };
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
				.setTitle("自定义列表项对话框").setIcon(R.drawable.ic_launcher);
		builder.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_1, items), null);
		setPositiveButton(builder);
		setNegativeButton(builder).create().show();
	}

	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder) {
		return builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "你单击了确定", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
		return builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "你单击了取消", Toast.LENGTH_SHORT)
						.show();

			}
		});
	}

	private void show(String msg) {
		Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
	}
}