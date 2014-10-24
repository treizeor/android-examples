package com.example.surfaceviewplayvideo;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	SurfaceView surfaceView;
	ImageButton play, pause, stop;
	MediaPlayer mPlayer;
	// 记录当前视频的播放位置
	int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		play = (ImageButton) findViewById(R.id.play);
		pause = (ImageButton) findViewById(R.id.pause);
		stop = (ImageButton) findViewById(R.id.stop);

		play.setOnClickListener(this);
		pause.setOnClickListener(this);
		stop.setOnClickListener(this);

		mPlayer = new MediaPlayer();
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		// 设置播放时打开屏幕
		surfaceView.getHolder().setKeepScreenOn(true);
		surfaceView.getHolder().addCallback(new SurfaceListener());

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			switch (v.getId()) {
			case R.id.play:
				play();
				break;
			case R.id.pause:
				if (mPlayer.isPlaying()) {
					mPlayer.pause();
				} else {
					mPlayer.start();
				}
				break;
			case R.id.stop:
				if (mPlayer.isLooping()) {
					mPlayer.stop();
				}
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void play() throws IOException {
		mPlayer.reset();
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		// 设置需要播放的视频
		mPlayer.setDataSource("/mnt/sdcard/video.mp4");
		// 把视频画面输出到SurfaceView
		mPlayer.setDisplay(surfaceView.getHolder());
		mPlayer.prepare();
		/*
		// 获取窗口管理器
		WindowManager wManager = getWindowManager();
		DisplayMetrics metrics = new DisplayMetrics();
		// 获取屏幕大小
		wManager.getDefaultDisplay().getMetrics(metrics);
		// 设置视频保持纵横比缩放占满整个屏幕
		surfaceView.setLayoutParams(new LayoutParams(metrics.widthPixels,
				mPlayer.getVideoHeight() * metrics.widthPixels
						/ mPlayer.getVideoWidth()));
		*/
		mPlayer.start();
	}

	private class SurfaceListener implements SurfaceHolder.Callback {
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// TODO Auto-generated method stub
			if (position > 0) {
				try {
					play();
					// 从指定位置开始播放
					mPlayer.seekTo(position);
					position = 0;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub

		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// TODO Auto-generated method stub

		}

	}

	// 当其他Activiy被打开时，暂停播放
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (mPlayer.isPlaying()) {
			// 保存当前播放位置
			position = mPlayer.getCurrentPosition();
			mPlayer.stop();
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (mPlayer.isPlaying()) {
			mPlayer.stop();
		}
		mPlayer.release();
		super.onDestroy();
	}

}