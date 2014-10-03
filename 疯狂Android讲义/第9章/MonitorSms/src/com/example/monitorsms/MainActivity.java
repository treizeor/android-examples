package com.example.monitorsms;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 为content://sms的数据改变注册监听器
		getContentResolver().registerContentObserver(
				Uri.parse("content://sms"), true,
				new SmsObserver(new Handler()));
	}

	private final class SmsObserver extends ContentObserver {

		public SmsObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			// 查询发送箱中的短信
			Cursor cursor = getContentResolver().query(
					Uri.parse("content://sms/outbox"), null, null, null, null);
			// 遍历查询结果集，即可获取用户正在发送到短信
			while (cursor.moveToNext()) {

				StringBuilder sb = new StringBuilder();
				// 获取发送到地址
				sb.append("address=").append(
						cursor.getString(cursor.getColumnIndex("address")));
				// 获取短信标题
				sb.append(";subject=").append(
						cursor.getString(cursor.getColumnIndex("subject")));
				// 获取短信内容
				sb.append(";body=").append(
						cursor.getString(cursor.getColumnIndex("body")));
				// 获取发送时间
				sb.append(";time=").append(
						cursor.getLong(cursor.getColumnIndex("date")));
				System.out.println("Has Sent SMS:" + sb.toString());
			}
		}

	}
}