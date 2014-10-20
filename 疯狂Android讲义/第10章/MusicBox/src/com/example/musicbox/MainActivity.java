package com.example.musicbox;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	EditText title, author;
	ImageButton play, stop;

	ActivityReceiver activityReceiver;

	public static final String CTL_ACTION = "cn.treize.action.CTL_ACTION";
	public static final String UPDATE_ACTION = "cn.treize.action.UPDATE_ACTION";

	// 定义播放状态, 0x11没有播放，0x12正在播放, 0x13暂停
	int status = 0x11;
	String[] titleStrs = new String[] { "心愿", "约定", "美丽新世界" };
	String[] authorStrs = new String[] { "未知艺术家", "周蕙", "伍佰" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		play = (ImageButton) findViewById(R.id.play);
		stop = (ImageButton) findViewById(R.id.stop);
		title = (EditText) findViewById(R.id.title);
		author = (EditText) findViewById(R.id.author);

		play.setOnClickListener(this);
		stop.setOnClickListener(this);

		activityReceiver = new ActivityReceiver();

		// 创建IntentFilter
		IntentFilter filter = new IntentFilter();
		// 指定BroadcastReceiver监听的action
		filter.addAction(UPDATE_ACTION);
		// 注册BroadcastReceiver
		registerReceiver(activityReceiver, filter);
		Intent intent = new Intent(this, MusicService.class);
		startService(intent);
	}

	// 自定义的BroadcastReceiver，负责监听从Service传回来的广播
	public class ActivityReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 获取Intent中的update消息，update代表播放状态
			int update = intent.getIntExtra("update", -1);
			// 获取Intent的current消息，current代表当前正在播放的歌曲
			int current = intent.getIntExtra("current", -1);
			if (current >= 0) {
				title.setText(titleStrs[current]);
				author.setText(authorStrs[current]);
			}
			switch (update) {
			case 0x11:
				play.setImageResource(R.drawable.play);
				status = 0x11;
				break;
			case 0x12:
				play.setImageResource(R.drawable.pause);
				status = 0x12;
				break;
			case 0x13:
				play.setImageResource(R.drawable.play);
				status = 0x13;
				break;
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(CTL_ACTION);
		switch (v.getId()) {
		case R.id.play:
			intent.putExtra("control", 1);
			break;
		
		case R.id.stop:
			intent.putExtra("control", 2);
			break;

		}
		// 发送广播，将被Service组件中的BroadcastReceiver接收到
		sendBroadcast(intent);
	}
}