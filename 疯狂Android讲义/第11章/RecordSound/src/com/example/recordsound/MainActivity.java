package com.example.recordsound;

import java.io.File;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button record, stop;
	File soundFile;
	MediaRecorder mRecorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		record = (Button) findViewById(R.id.button1);
		stop = (Button) findViewById(R.id.button2);

		record.setOnClickListener(this);
		stop.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (soundFile != null && soundFile.exists()) {
			// 停止录音
			mRecorder.stop();
			// 释放资源
			mRecorder.release();
			mRecorder = null;
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			if (!Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) {
				Toast.makeText(MainActivity.this, "SD卡不存在，请插入SD卡",
						Toast.LENGTH_LONG).show();
				return;
			}
			try {
				// 创建保存录音的音频文件
				soundFile = new File(Environment.getExternalStorageDirectory()
						.getCanonicalFile() + "/sound.amr");
				mRecorder = new MediaRecorder();
				// 设置录音的声音来源
				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				// 设置录制的声音的输出格式（必须在设置声音编码格式之前设置）
				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				// 设置录制声音的编码格式
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				mRecorder.setOutputFile(soundFile.getAbsolutePath());
				mRecorder.prepare();
				mRecorder.start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;

		case R.id.button2:
			if (soundFile != null && soundFile.exists()) {
				mRecorder.stop();
				mRecorder.release();
				mRecorder = null;
			}
			break;
		}
	}
}