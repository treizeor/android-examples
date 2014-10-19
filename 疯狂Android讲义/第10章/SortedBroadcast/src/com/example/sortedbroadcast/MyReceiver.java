package com.example.sortedbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(
				context,
				"Intent.Action:" + intent.getAction() + "\nmsg:"
						+ intent.getStringExtra("msg"), Toast.LENGTH_SHORT)
				.show();
		// 创建一个Bundle对象并存入数据
		Bundle bundle = new Bundle();
		bundle.putString("first", "第一个BroadcastReceiver存入的信息");
		// 将bundle放入结果中
		setResultExtras(bundle);
		// 取消Broadcast继续传播
		// abortBroadcast(); // 取消注释将不会传播到MyReceiver2
	}

}
