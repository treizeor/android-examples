package com.example.contactprovidertest;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button search = (Button) findViewById(R.id.search);

		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 定义两个List来封装系统的联系人信息、指定联系人的电话号码、Email的详情
				final ArrayList<String> names = new ArrayList<String>();
				final ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();
				// 使用ContentResolver查找联系人
				Cursor cursor = getContentResolver().query(
						ContactsContract.Contacts.CONTENT_URI, null, null,
						null, null);
				// 遍历查询结果，获取系统中所有联系人
				while (cursor.moveToNext()) {
					// 获取联系人Id
					String contactId = cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts._ID));
					// 获取联系人的名字
					String name = cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					names.add(name);
					// 使用contentResolver查找联系人的电话
					Cursor phones = getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactId, null, null);
					ArrayList<String> detail = new ArrayList<String>();
					// 遍历查询结果，获取该联系人的多个电话号码
					while (phones.moveToNext()) {
						// 获取查询结果中电话号码列中的数据
						String phoneNumber = phones.getString(phones
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						detail.add("电话号码:" + phoneNumber);
					}
					phones.close();
					// 使用ContentResolver查找联系人的Email地址
					Cursor emails = getContentResolver().query(
							ContactsContract.CommonDataKinds.Email.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Email.CONTACT_ID
									+ " = " + contactId, null, null);
					// 遍历查询结果获取该联系人的多个Email地址
					while (emails.moveToNext()) {
						String emailAddress = emails.getString(emails
								.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
						detail.add("邮件地址:" + emailAddress);
					}
					emails.close();
					details.add(detail);
				}
				cursor.close();

				// 加载result.xml界面
				View resultDialog = getLayoutInflater().inflate(
						R.layout.result, null);
				// 获取resultDialog中的ExpandableListView
				ExpandableListView listView = (ExpandableListView) resultDialog
						.findViewById(R.id.expandableListView1);
				// 创建一个ExpandableListAdapter对象
				ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

					@Override
					public boolean isChildSelectable(int groupPosition,
							int childPosition) {
						// TODO Auto-generated method stub
						return true;
					}

					@Override
					public boolean hasStableIds() {
						// TODO Auto-generated method stub
						return true;
					}

					@Override
					public View getGroupView(int groupPosition,
							boolean isExpanded, View convertView,
							ViewGroup parent) {
						// TODO Auto-generated method stub
						TextView textView = getTextView();
						textView.setText(getGroup(groupPosition).toString());
						return textView;
					}

					@Override
					public long getGroupId(int groupPosition) {
						// TODO Auto-generated method stub
						return groupPosition;
					}

					@Override
					public int getGroupCount() {
						// TODO Auto-generated method stub
						return names.size();
					}

					@Override
					public Object getGroup(int groupPosition) {
						// TODO Auto-generated method stub
						return names.get(groupPosition);
					}

					@Override
					public int getChildrenCount(int groupPosition) {
						// TODO Auto-generated method stub
						return details.get(groupPosition).size();
					}

					@Override
					public View getChildView(int groupPosition,
							int childPosition, boolean isLastChild,
							View convertView, ViewGroup parent) {
						// TODO Auto-generated method stub
						TextView textView = getTextView();
						textView.setText(getChild(groupPosition, childPosition)
								.toString());
						return textView;
					}

					@Override
					public long getChildId(int groupPosition, int childPosition) {
						// TODO Auto-generated method stub
						return childPosition;
					}

					// 获取指定组位置、指定子列表项处子列表项数据
					@Override
					public Object getChild(int groupPosition, int childPosition) {
						// TODO Auto-generated method stub
						return details.get(groupPosition).get(childPosition);
					}

					private TextView getTextView() {
						AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
								ViewGroup.LayoutParams.MATCH_PARENT, 64);
						TextView textView = new TextView(MainActivity.this);
						textView.setLayoutParams(lp);
						textView.setGravity(Gravity.CENTER_VERTICAL
								| Gravity.LEFT);
						textView.setPadding(36, 0, 0, 0);
						textView.setTextSize(20);
						return textView;
					}
				};
				listView.setAdapter(adapter);
				// 使用对话框来显示查询结果
				new AlertDialog.Builder(MainActivity.this,
						AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
						.setView(resultDialog).setPositiveButton("确定", null)
						.show();
			}
		});

		Button add = (Button) findViewById(R.id.add);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = ((EditText) findViewById(R.id.name)).getText()
						.toString();
				String phone = ((EditText) findViewById(R.id.phone)).getText()
						.toString();
				String email = ((EditText) findViewById(R.id.email)).getText()
						.toString();
				// 创建一个空的ContentValues
				ContentValues values = new ContentValues();
				// 向rawContacts.CONTENT_URI执行一个空值插入
				// 目的是获取系统返回的rawContactId
				Uri rawContactUri = getContentResolver().insert(
						RawContacts.CONTENT_URI, values);
				long rawContactId = ContentUris.parseId(rawContactUri);
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				// 设置内容类型
				values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
				// 设置联系人名字
				values.put(StructuredName.GIVEN_NAME, name);
				// 向联系人URI添加联系人名字
				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
				// 设置联系人电话号码
				values.put(Phone.NUMBER, phone);
				// 设置电话类型
				values.put(Phone.TYPE, Phone.TYPE_MOBILE);
				// 向联系人电话号码URI添加电话号码
				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
				values.put(Email.DATA, email);
				values.put(Email.TYPE, Email.TYPE_WORK);
				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				Toast.makeText(MainActivity.this, "联系人数据添加成功.", 0).show();

			}
		});
	}
}