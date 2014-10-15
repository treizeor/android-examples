package com.example.monitorphone;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.graphics.AvoidXfermode.Mode;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.style.SuperscriptSpan;

public class MainActivity extends Activity {

	TelephonyManager tm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		// 创建一个通话状态监听器
		PhoneStateListener listener = new PhoneStateListener() {
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				// TODO Auto-generated method stub
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE:
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					break;
				case TelephonyManager.CALL_STATE_RINGING:
					// 来电
					OutputStream os = null;
					try {
						os = openFileOutput("phoneList", MODE_APPEND);
					} catch (Exception e) {
						// TODO: handle exception
					}
					PrintStream ps = new PrintStream(os);
					ps.println(new Date() + "来电" + incomingNumber);
					ps.close();
					break;
				default:
					break;
				}
				super.onCallStateChanged(state, incomingNumber);
			}
		};
		// 监听通话状态的改变
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
}