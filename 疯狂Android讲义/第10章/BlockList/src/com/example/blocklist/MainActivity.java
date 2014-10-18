package com.example.blocklist;

import java.lang.reflect.Method;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.android.internal.telephony.ITelephony;

public class MainActivity extends Activity {
	// 记录黑名单的List
	ArrayList<String> blockList = new ArrayList<String>();
	TelephonyManager tm;

	// 监听通话状态的监听器
	CustomPhoneCallListener cpListener;

	public class CustomPhoneCallListener extends PhoneStateListener {
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				if (isBlock(incomingNumber)) {
					try {
						// 获取android.os.ServiceManager类的对象的getService()方法
						Method method = Class.forName(
								"android.os.ServiceManager").getMethod(
								"getService", String.class);
						// 获取远程TELEPHONY_SERVICE的IBinder对象的代理
						IBinder binder = (IBinder) method.invoke(null,
								new Object[] { TELEPHONY_SERVICE });
						// 将IBinder对象的代理转换为ITelephony对象
						ITelephony telephony = ITelephony.Stub
								.asInterface(binder);
						// 挂断电话
						telephony.endCall();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取系统的TelephonyManager管理器
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		cpListener = new CustomPhoneCallListener();
		// 监听通话状态的改变
		tm.listen(cpListener, PhoneStateListener.LISTEN_CALL_STATE);
		findViewById(R.id.button1).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// 查询联系人电话号码
						final Cursor cursor = getContentResolver()
								.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
										null, null, null, null);
						BaseAdapter adapter = new BaseAdapter() {
							@Override
							public View getView(int position, View convertView,
									ViewGroup parent) {
								// TODO Auto-generated method stub
								cursor.moveToPosition(position);
								CheckBox rb = new CheckBox(MainActivity.this);
								// 获取联系人的电话号码，并去掉中间的中画线
								String number = cursor
										.getString(
												cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
										.replace("-", "");
								rb.setText(number);
								// 如果该号码已经被加入黑名单，默认勾选该号码
								if (isBlock(number)) {
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

						// 加载list.xml布局文件
						View selectView = getLayoutInflater().inflate(
								R.layout.list, null);
						// 获取selectView中的ListView组件
						//final ListView listView = (ListView) findViewById(R.id.listView1);
						// 出错原因，listView是属于selectView中的。。。。
						final ListView listView = (ListView) selectView
								.findViewById(R.id.listView1);
						listView.setAdapter(adapter);
						new AlertDialog.Builder(MainActivity.this)
								.setView(selectView)
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												// 清空blockList集合
												blockList.clear();
												// 遍历ListView组件的每个列表项
												for (int i = 0; i < listView
														.getCount(); i++) {
													CheckBox checkBox = (CheckBox) listView
															.getChildAt(i);
													// 如果该列表项被勾选
													if (checkBox.isChecked()) {
														// 添加该列表项的电话号码
														blockList.add(checkBox
																.getText()
																.toString());
													}
												}
												System.out.println(blockList);
											}

										}).show();
					}
				});
	}

	// 判断某个号码是否在黑名单内
	public boolean isBlock(String phone) {
		for (String s1 : blockList) {
			if (s1.equals(phone)) {
				return true;
			}
		}
		return false;
	}
}