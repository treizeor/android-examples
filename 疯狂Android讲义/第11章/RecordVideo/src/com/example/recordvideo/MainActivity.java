package com.example.recordvideo;

import java.io.File;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	ImageButton record, stop;
	File videoFile;
	MediaRecorder mRecorder;
	SurfaceView sView;
	// 记录是否正在进行
	private boolean isRecording = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		record = (ImageButton) findViewById(R.id.record);
		stop = (ImageButton) findViewById(R.id.stop);
		stop.setEnabled(false);
		record.setOnClickListener(this);
		stop.setOnClickListener(this);

		sView = (SurfaceView) findViewById(R.id.sView);
		// 设置分辨率
		sView.getHolder().setFixedSize(1280, 720);
		// 设置该组件让屏幕不会自动关闭
		sView.getHolder().setKeepScreenOn(true);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.record:
			if (!Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED)) {
				Toast.makeText(MainActivity.this, "SD卡不存在，请插入SD卡",
						Toast.LENGTH_LONG).show();
				return;
			}
			try {
				videoFile = new File(Environment.getExternalStorageDirectory()
						.getCanonicalFile() + "/myvideo.3gp");
				mRecorder = new MediaRecorder();
				mRecorder.reset();
				// 设置从麦克风采集声音
				mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				// 设置从摄像头采集图像
				mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
				// 设置视频文件输出格式，必须在声音编码，图像编码前设置
				mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				// 设置声音编码格式
				mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				// 设置图像编码格式
				mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
				mRecorder.setVideoSize(1280, 720);
				// 每秒4帧
				mRecorder.setVideoFrameRate(20);
				mRecorder.setOutputFile(videoFile.getAbsolutePath());
				// 指定使用sView预览
				mRecorder.setPreviewDisplay(sView.getHolder().getSurface());
				mRecorder.prepare();
				mRecorder.start();
				System.out.println("--------Recording--------");
				Toast.makeText(MainActivity.this, "Recording...",
						Toast.LENGTH_LONG).show();
				record.setEnabled(false);
				stop.setEnabled(true);
				isRecording = true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case R.id.stop:
			if (isRecording) {
				mRecorder.stop();
				mRecorder.release();
				mRecorder = null;
				record.setEnabled(true);
				stop.setEnabled(false);
				isRecording = false;
				Toast.makeText(MainActivity.this, "Stop...", Toast.LENGTH_LONG)
						.show();
			}
			break;
		}
	}

}