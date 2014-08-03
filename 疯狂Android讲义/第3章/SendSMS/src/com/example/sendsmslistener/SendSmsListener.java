package com.example.sendsmslistener;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.sip.SipAudioCall.Listener;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class SendSmsListener implements OnClickListener {

	private Activity act;
	private EditText phoneNum, content;

	public SendSmsListener(Activity act, EditText phoneNum, EditText content) {
		this.act = act;
		this.phoneNum = phoneNum;
		this.content = content;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String phoneNubStr = phoneNum.getText().toString();
		String contentStr = content.getText().toString();
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent sentIntent = PendingIntent.getBroadcast(act, 0,
				new Intent(), 0);
		smsManager.sendTextMessage(phoneNubStr, null, contentStr, sentIntent,
				null);
		Toast.makeText(act, "短信发送完成", Toast.LENGTH_LONG).show();

	}

}
