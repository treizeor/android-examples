package com.example.rawrestest;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	MediaPlayer mediaPlayer1 = null;
	MediaPlayer mediaPlayer2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.button1);
		Button button2 = (Button)findViewById(R.id.button2);
		
		mediaPlayer1 = MediaPlayer.create(this, R.raw.ring);
		
		// 获取AssetMannager
		AssetManager am = getAssets();
		
		try {
			// 获取指定文件对应的AssetFileDescriptor
			AssetFileDescriptor afd = am.openFd("ring2.mp3");
			mediaPlayer2 = new MediaPlayer();
			// 使用MediaPlayer加载指定的声音文件
			mediaPlayer2.setDataSource(afd.getFileDescriptor());
			mediaPlayer2.prepare();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer1.start();
			}
		});
		
		
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer2.start();
			}
		});
	}
}