package com.example.sortedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Bundle bundle = getResultExtras(true);
		String first = bundle.getString("first");
		Toast.makeText(context, "Form MyReceiver:" + first, Toast.LENGTH_LONG)
				.show();
	}

}
