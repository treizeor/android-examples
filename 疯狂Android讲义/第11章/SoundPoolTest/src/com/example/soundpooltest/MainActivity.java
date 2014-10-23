package com.example.soundpooltest;

import java.util.HashMap;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button ring1, ring2, ring3;
	// 定义一个SoundPool
	SoundPool soundPool;
	// 使用HashMap<Integer, Integer>管理SoundPool加载的每个声音ID
	HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ring1 = (Button)findViewById(R.id.button1);
		ring2 = (Button)findViewById(R.id.button2);
		ring3 = (Button)findViewById(R.id.button3);
		// 设置最多容纳10个音频流，音频的品质为5
		soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		// load方法加载指定音频文件，并返回所加载的音频ID
		// 此处使用HashMap管理这些音频流
		soundMap.put(1, soundPool.load(this, R.raw.ring1, 1));
		soundMap.put(2, soundPool.load(this, R.raw.ring2, 2));
		soundMap.put(3, soundPool.load(this, R.raw.ring3, 3));
		
		ring1.setOnClickListener(this);
		ring2.setOnClickListener(this);
		ring3.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
			break;
		case R.id.button2:
			soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			break;
		case R.id.button3:
			soundPool.play(soundMap.get(3), 1, 1, 0, 0, 1);
			break;
		}
	}
}