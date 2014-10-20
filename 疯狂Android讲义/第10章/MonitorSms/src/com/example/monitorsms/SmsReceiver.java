package com.example.monitorsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction()
				.equals("android.provider.Telephony.SMS_RECEIVED")) {
			// 取消广播，这行代码会让系统接收不到短信
			abortBroadcast();
			StringBuilder sb = new StringBuilder();
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				// 通过pdus可以获取接收到的所有短信
				Object[] pdus = (Object[])bundle.get("pdus");
				// 构建短信对象array，并依据收到的对象长度来创建array大小
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) {
					messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				}
				// 将发来的短信合并自定义信息于StringBuilder当中
				for (SmsMessage smsMessage : messages) {
					sb.append("From:");
					sb.append(smsMessage.getDisplayOriginatingAddress());
					sb.append("Content:");
					sb.append(smsMessage.getDisplayMessageBody());
				}
			}
			Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
		}
	}

}
