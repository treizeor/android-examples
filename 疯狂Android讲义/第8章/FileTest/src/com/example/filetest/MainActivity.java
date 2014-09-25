package com.example.filetest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	final String FILE_NAME = "io.bin";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println(new StringBuilder("a").append("b").append("c")
				.toString());
		Button read = (Button) findViewById(R.id.read);
		Button write = (Button) findViewById(R.id.write);
		final EditText text1 = (EditText) findViewById(R.id.editText1);
		final EditText text2 = (EditText) findViewById(R.id.editText2);

		write.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 将EditText1 的文字写入文件中
				write(text1.getText().toString());
				text1.setText("");
			}

		});

		read.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 读取指定文件中的内容并显示在text2中
				text2.setText(read());

			}

		});
	}

	private String read() {
		// TODO Auto-generated method stub
		try {
			// 打开文件输入流
			FileInputStream fis = openFileInput(FILE_NAME);
			byte[] buff = new byte[1024];
			int hasRead = 0;
			StringBuilder sb = new StringBuilder("");
			// 读取文件内容
			while ((hasRead = fis.read(buff)) > 0) {
				sb.append(new String(buff, 0, hasRead));
			}
			// 关闭文件输入流
			fis.close();
			return sb.toString();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	private void write(String content) {
		// TODO Auto-generated method stub
		try {
			// 以追加模式打开文件输出流
			FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
			// 将FileOutputStream包装成PrintStream
			PrintStream ps = new PrintStream(fos);
			// 输出文件内容
			ps.println(content);
			// 关闭文件输出流
			ps.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}