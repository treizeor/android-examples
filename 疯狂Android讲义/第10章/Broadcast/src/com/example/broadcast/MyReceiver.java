package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(
				context,
				"接收到的Intent的Action为：" + intent.getAction() + "\n消息内容为:"
						+ intent.getStringExtra("msg"), Toast.LENGTH_SHORT)
				.show();
	}

}
