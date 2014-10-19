package com.example.alarmtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;

public class AlarmActivity extends Activity{
	MediaPlayer mPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mPlayer = MediaPlayer.create(AlarmActivity.this, R.raw.ring2);
		mPlayer.setLooping(true);
		mPlayer.start();
		new AlertDialog.Builder(AlarmActivity.this).setTitle("闹钟").setMessage("闹钟闹钟你别闹~~").setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// 停止音乐
				mPlayer.stop();
				AlarmActivity.this.finish();
			}
		}).show();
	}
}
