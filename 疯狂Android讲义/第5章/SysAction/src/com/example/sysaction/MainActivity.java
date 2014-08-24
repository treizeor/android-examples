package com.example.sysaction;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	final int PICK_CONTACT = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button check = (Button) findViewById(R.id.check);
		check.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.setType("vnd.android.cursor.item/phone");
				startActivityForResult(intent, PICK_CONTACT);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (PICK_CONTACT):
			if (resultCode == Activity.RESULT_OK) {
				// 获取返回的数据
				Uri contactData = data.getData();
				CursorLoader cursorLoader = new CursorLoader(this, contactData,
						null, null, null, null);
				// 查询联系人信息
				Cursor cursor = cursorLoader.loadInBackground();
				// 如果查询到指定联系人
				if (cursor.moveToFirst()) {
					String contactID = cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts._ID));
					// 获取联系人的名字
					String name = cursor
							.getString(cursor
									.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
					String phoneNum = "此联系人暂未输入电话号码";
					// 根据联系人查询该联系人的详细信息
					Cursor phones = getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
							null, null);
					if (phones.moveToFirst()) {
						// 取出电话号码
						phoneNum = phones
								.getString(phones
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					}
					// 关闭游标
					phones.close();
					EditText show = (EditText)findViewById(R.id.person);
					show.setText(name);
					EditText phone = (EditText)findViewById(R.id.phone);
					phone.setText(phoneNum);
				}
				//关闭游标
				cursor.close();
			}
			break;
		}
	}
}