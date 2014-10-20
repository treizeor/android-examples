package com.example.musicbox;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

public class MusicService extends Service {

	MyReceiver serviceReceiver;
	AssetManager am;
	String[] musics = new String[] { "wish.mp3", "promise.mp3", "beautiful.mp3" };
	MediaPlayer mPlayer;
	// 定义播放状态, 0x11没有播放，0x12正在播放, 0x13暂停
	int status = 0x11;
	// 当前在播音乐
	int current = 0;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		am = getAssets();
		serviceReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(MainActivity.CTL_ACTION);
		registerReceiver(serviceReceiver, filter);
		mPlayer = new MediaPlayer();
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				current ++;
				if (current >= 3) {
					current = 0;
				}
				Intent sendIntent = new Intent(MainActivity.UPDATE_ACTION);
				sendIntent.putExtra("current", current);
				sendBroadcast(sendIntent);
				prepareAndPlay(musics[current]);
			}
		});
		
		super.onCreate();
	}

	public class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			int control = intent.getIntExtra("control", -1);
			switch (control) {
			// 播放或暂停
			case 1:
				// 原来处于没播放状态
				if (status == 0x11) {
					// 准备并播放音乐
					prepareAndPlay(musics[current]);
					status = 0x12;
				} // 原来处于播放状态
				else if (status == 0x12) {
					mPlayer.pause();
					status = 0x13;
				} else if (status == 0x13) {
					mPlayer.start();
					status = 0x12;
				}
				break;
			case 2:
				// 如果原来处于播放或暂停
				if (status == 0x12 || status == 0x13) {
					mPlayer.stop();
					status = 0x11;
				}
			}
			// 广播通知Activity更改图标，文本框
			Intent sendIntent = new Intent(MainActivity.UPDATE_ACTION);
			sendIntent.putExtra("update", status);
			sendIntent.putExtra("current", current);
			// 发送广播，将被Activity组件中的BroadcastReceiver接收到
			sendBroadcast(sendIntent);
		}

	}

	private void prepareAndPlay(String music) {
		try {
			// 打开指定音乐文件
			AssetFileDescriptor afd = am.openFd(music);
			mPlayer.reset();
			// 使用MediaPlayer加载指定的声音文件
			mPlayer.setDataSource(afd.getFileDescriptor(),
					afd.getStartOffset(), afd.getLength());
			// 准备声音
			mPlayer.prepare();
			// 播放
			mPlayer.start();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
