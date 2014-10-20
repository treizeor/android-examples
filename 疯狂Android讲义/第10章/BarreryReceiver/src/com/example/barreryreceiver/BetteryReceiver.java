package com.example.barreryreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BetteryReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = intent.getExtras();
		// 获取当前电量
		int current = bundle.getInt("level");
		// 获取总电量
		int total = bundle.getInt("scale");
		if (current * 1.0 / total < 0.15) {
			Toast.makeText(context, "电量过低", Toast.LENGTH_LONG).show();
		}
	}

}
