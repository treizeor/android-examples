package com.example.audiotest;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	Button play, up, down;
	ToggleButton mute;
	AudioManager aManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		aManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		play = (Button) findViewById(R.id.play);
		up = (Button) findViewById(R.id.up);
		down = (Button) findViewById(R.id.down);
		mute = (ToggleButton) findViewById(R.id.mute);

		play.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this,
						R.raw.ring2);
				// 设置循环播放
				mPlayer.setLooping(true);
				mPlayer.start();
			}
		});

		up.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 指定调节音乐的音频，增大音量，而且显示音量图形示意
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
						AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);

			}
		});

		down.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 指定调节音乐的音频，降低音量，而且显示音量图形示意
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
						AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);

			}
		});

		mute.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// 指定调节音乐音频，根据ischecked确定是否静音
				aManager.setStreamMute(AudioManager.STREAM_MUSIC, isChecked);

			}
		});
	}
}