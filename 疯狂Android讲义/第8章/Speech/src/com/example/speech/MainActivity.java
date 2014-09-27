package com.example.speech;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextToSpeech tts;
	EditText editText;
	Button speech;
	Button record;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tts = new TextToSpeech(this, new OnInitListener() {

			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				// 如果装载TTS引擎成功
				if (status == TextToSpeech.SUCCESS) {
					// 设置使用美式英语朗读
					int result = tts.setLanguage(Locale.US);
					// 如果不支持所设置的语言
					if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
							&& result != TextToSpeech.LANG_AVAILABLE) {
						Toast.makeText(MainActivity.this, "TTS暂不支持该语言", 0)
								.show();
					}
				}
			}
		});
		editText = (EditText) findViewById(R.id.text);
		speech = (Button) findViewById(R.id.speech);
		record = (Button) findViewById(R.id.record);

		speech.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 执行朗读
				tts.speak(editText.getText().toString(),
						TextToSpeech.QUEUE_ADD, null);
			}
		});

		record.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 将朗读文本的音频记录到指定文件
				tts.synthesizeToFile(editText.getText().toString(), null,
						"/mnt/sdcard/sound.wav");
				Toast.makeText(MainActivity.this, "声音记录成功", 0).show();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 关闭tts对象
		tts.shutdown();
	}
}