package com.example.groupsend;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText numbers, content;
	Button select, send;
	SmsManager smsManager;

	ArrayList<String> sendList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		smsManager = SmsManager.getDefault();
		numbers = (EditText) findViewById(R.id.number);
		content = (EditText) findViewById(R.id.content);
		select = (Button) findViewById(R.id.select);
		send = (Button) findViewById(R.id.send);

		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 遍历sendList，逐个发送
				for (String number : sendList) {
					PendingIntent pi = PendingIntent.getActivity(
							MainActivity.this, 0, new Intent(), 0);
					smsManager.sendTextMessage(number, null, content.getText()
							.toString(), pi, null);
				}
				Toast.makeText(MainActivity.this, "短信发送完成", Toast.LENGTH_SHORT)
						.show();
			}
		});

		select.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Cursor cursor = getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null, null, null, null);
				BaseAdapter adapter = new BaseAdapter() {

					@Override
					public View getView(int position, View convertView,
							ViewGroup parent) {
						// TODO Auto-generated method stub
						cursor.moveToPosition(position);
						CheckBox rb = new CheckBox(MainActivity.this);
						String number = cursor
								.getString(
										cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
								.replace("-", "").replace(" ", "");
						rb.setText(number);
						if (isChecked(number)) {
							rb.setChecked(true);
						}
						return rb;
					}

					@Override
					public long getItemId(int position) {
						// TODO Auto-generated method stub
						return position;
					}

					@Override
					public Object getItem(int position) {
						// TODO Auto-generated method stub
						return position;
					}

					@Override
					public int getCount() {
						// TODO Auto-generated method stub
						return cursor.getCount();
					}
				};

				// 加载list.xml
				View selectView = getLayoutInflater().inflate(R.layout.list,
						null);
				final ListView listView = (ListView) selectView
						.findViewById(R.id.listView1);
				listView.setAdapter(adapter);

				new AlertDialog.Builder(MainActivity.this)
						.setView(selectView)
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										sendList.clear();
										for (int i = 0; i < listView.getCount(); i++) {
											CheckBox cb = (CheckBox) listView
													.getChildAt(i);
											if (cb.isChecked()) {
												sendList.add(cb.getText()
														.toString());
											}
											numbers.setText(sendList.toString());
										}
									}
								}).show();
			}
		});
	}

	public boolean isChecked(String phone) {
		for (String s1 : sendList) {
			if (s1.equals(phone)) {
				return true;
			}
		}
		return false;
	}
}