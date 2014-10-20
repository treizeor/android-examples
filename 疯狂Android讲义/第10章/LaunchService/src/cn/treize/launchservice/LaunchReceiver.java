package cn.treize.launchservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LaunchReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent tIntent = new Intent(context, LaunchService.class);
		context.startService(tIntent);
	}

}
