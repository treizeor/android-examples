package com.example.proximitytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class ProximityAlertReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// 获取是否为进入指定区域
		boolean isEnter = intent.getBooleanExtra(
				LocationManager.KEY_PROXIMITY_ENTERING, false);
		if (isEnter) {
			Toast.makeText(context, "您已经进入禁区", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, "您已经离开禁区", Toast.LENGTH_LONG).show();
		}
	}

}
