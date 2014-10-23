package com.example.videoviewtest;

import java.io.File;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	VideoView videoView;
	MediaController mController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.activity_main);
		videoView = (VideoView) findViewById(R.id.video);
		// 创建MediaController对象
		mController = new MediaController(this);
		File video = new File("/mnt/sdcard/video.mp4");
		if (video.exists()) {
			videoView.setVideoPath(video.getAbsolutePath());
			// 设置videoView与mController建立关联
			videoView.setMediaController(mController);
			// 设置mController与videoView建立关联
			mController.setMediaPlayer(videoView);
			// 让VideoView获取焦点
			videoView.requestFocus();
		}
	}
}