package com.example.jscallandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MyObject {

	Context context;

	public MyObject(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	// 该方法将会暴露给JavaScript脚本调用 ，Android4.2以上必须声明@JavascriptInterface
	@JavascriptInterface
	public void showToast(String name) {
		Toast.makeText(context, name + ", 你好!", Toast.LENGTH_LONG).show();
	}

	// 该方法将会暴露给JavaScript脚本调用 ，Android4.2以上必须声明@JavascriptInterface
	@JavascriptInterface
	public void showList() {
		// 显示一个普通的列表对话框
		new AlertDialog.Builder(context)
				.setTitle("图书列表")
				.setIcon(R.drawable.ic_launcher)
				.setItems(
						new String[] { "Crazy Android", "学习Golang",
								"Code Complete" }, null)
				.setPositiveButton("确定", null).create().show();
	}
}
