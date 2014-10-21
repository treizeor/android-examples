package com.example.mediaplaytest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.PresetReverb;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	// 定义播放声音的MediaPlayer
	private MediaPlayer mPlayer;
	// 定义系统的示波器
	private Visualizer mVisualizer;
	// 定义系统的均衡器
	private Equalizer mEqualizer;
	// 定义系统的重低音控制器
	private BassBoost mBassBoost;
	// 预设音场控制器
	private PresetReverb mPresetReverb;
	private List<Short> reverbName = new ArrayList<Short>();
	private List<String> reverbVal = new ArrayList<String>();

	private LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置控制音乐声音
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		// 创建MediaPlayer对象
		mPlayer = MediaPlayer.create(this, R.raw.ring2);
		// 初始化示波器
		// setupVisualizer();
		// 初始化均衡控制器
		setupEqualizer();
		// 初始化重低音控制器
		setupBassBoost();
		// 初始化预设音场控制器
		setupPreSetReverb();
		mPlayer.start();
	}

	// 初始化示波器的方法
	private void setupVisualizer() {
		// 创建MyVisualizerView组件 ，用于显示波形图
		final MyVisualizerView mVisualizerView = new MyVisualizerView(this);
		mVisualizerView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				(int) (120f * getResources().getDisplayMetrics().density)));
		layout.addView(mVisualizerView);
		// 以MediaPlayer的AudioSessionId创建Visualizer
		// 相当于设置Visualizer负责显示该MediaPlayer的音频数据
		mVisualizer = new Visualizer(mPlayer.getAudioSessionId());
		mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
		// 为mVisualizer设置监听器
		mVisualizer.setDataCaptureListener(
				new Visualizer.OnDataCaptureListener() {

					@Override
					public void onWaveFormDataCapture(Visualizer visualizer,
							byte[] waveform, int samplingRate) {
						// 用waveform波形数据根系mVisualizerView组件
						mVisualizerView.updateVisualizer(waveform);
					}

					@Override
					public void onFftDataCapture(Visualizer visualizer,
							byte[] fft, int samplingRate) {
						// TODO Auto-generated method stub

					}
				}, Visualizer.getMaxCaptureRate() / 2, true, false);
		mVisualizer.setEnabled(true);
	}

	// ---初始化均衡控制器的方法
	private void setupEqualizer() {
		// 以MeidaPlayer的AudioSessionId创建Equalizer
		// 相当于设置Equalizer负责控制该MediaPlayer
		mEqualizer = new Equalizer(0, mPlayer.getAudioSessionId());
		// 启动均衡器控制效果
		mEqualizer.setEnabled(true);
		TextView eqTitle = new TextView(this);
		eqTitle.setText("均衡器");
		layout.addView(eqTitle);
		// 获取均衡控制器支持最小值和最大值
		final short minEQLevel = mEqualizer.getBandLevelRange()[0];
		short maxEQLevel = mEqualizer.getBandLevelRange()[1];
		// 获取均衡器支持的所有频率
		short brands = mEqualizer.getNumberOfBands();
		for (short i = 0; i < brands; i++) {
			// 创建一个TextView，用于显示频率
			TextView eqTextView = new TextView(this);
			eqTextView.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			eqTextView.setGravity(Gravity.CENTER_HORIZONTAL);
			// 设置该均衡器的频率
			eqTextView.setText((mEqualizer.getCenterFreq(i) / 1000) + "HZ");
			layout.addView(eqTextView);
			// 创建一个水平排列的组件的LinearLayout
			LinearLayout tmpLayout = new LinearLayout(this);
			tmpLayout.setOrientation(LinearLayout.HORIZONTAL);
			// 创建显示均衡控制器最小值的TextView
			TextView minDbTextView = new TextView(this);
			minDbTextView.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			// 显示均衡控制器的最小值
			minDbTextView.setText((minEQLevel / 100) + "dB");

			// 创建显示均衡控制器最大值的TextView
			TextView maxDbTextView = new TextView(this);
			maxDbTextView.setLayoutParams(new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			// 显示均衡控制器的最大值
			maxDbTextView.setText((maxEQLevel / 100) + "dB");

			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParams.weight = 1;
			// 定义SeekBar作为调整工具
			SeekBar bar = new SeekBar(this);
			bar.setLayoutParams(layoutParams);
			bar.setMax(maxEQLevel - minEQLevel);
			bar.setProgress(mEqualizer.getBandLevel(i));
			final short brand = i;
			// 为SeekBar的拖动事件设定监听器
			bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
						boolean fromUser) {
					// TODO Auto-generated method stub
					// 设置该频率的均衡值
					mEqualizer.setBandLevel(brand,
							(short) (progress + minEQLevel));
				}
			});
			// 使用水平排列组件的LinearLayout包装三个组件
			tmpLayout.addView(minDbTextView);
			tmpLayout.addView(bar);
			tmpLayout.addView(maxDbTextView);
			// 将该水平LinearLayout添加到主layout中
			layout.addView(tmpLayout);
		}
	}

	// ---初始化重低音控制器的方法
	private void setupBassBoost() {
		mBassBoost = new BassBoost(0, mPlayer.getAudioSessionId());
		mBassBoost.setEnabled(true);
		TextView bbTitle = new TextView(this);
		bbTitle.setText("重低音");
		layout.addView(bbTitle);
		SeekBar bar = new SeekBar(this);
		bar.setMax(1000);
		bar.setProgress(0);
		bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				mBassBoost.setStrength((short) progress);
			}
		});
		layout.addView(bar);
	}

	// ----初始化预设音场控制器
	private void setupPreSetReverb() {
		mPresetReverb = new PresetReverb(0, mPlayer.getAudioSessionId());
		mPresetReverb.setEnabled(true);
		TextView prTitle = new TextView(this);
		prTitle.setText("音场");
		layout.addView(prTitle);
		// 获取系统支持的所有预设音场
		for (short i = 0; i < mEqualizer.getNumberOfPresets(); i++) {
			reverbName.add(i);
			reverbVal.add(mEqualizer.getPresetName(i));
		}
		// 使用Spinner作为音场选择工具
		Spinner sp = new Spinner(this);
		sp.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_spinner_item, reverbVal));
		sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mPresetReverb.setPreset(reverbName.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		layout.addView(sp);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (isFinishing() && mPlayer != null) {
			// 释放所有对象
			mVisualizer.release();
			mEqualizer.release();
			mPresetReverb.release();
			mBassBoost.release();
			mPlayer.release();
			mPlayer = null;
		}
	}

}