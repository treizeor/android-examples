package com.example.simpleclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText show;
	Button connect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (EditText) findViewById(R.id.editText1);
		connect = (Button) findViewById(R.id.connect);
		connect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						try {
							// 建立Socket连接到远程服务器
							Socket s = new Socket("192.168.3.102", 20001);
							System.out.println("--connect---");
							// 将Socket对应的输入流包装成BufferedReader
							BufferedReader br = new BufferedReader(
									new InputStreamReader(s.getInputStream()));
							// 进行普通I/O操作
							String line = br.readLine();
							System.out.println("---"+line);
							Toast.makeText(MainActivity.this, line, Toast.LENGTH_LONG).show();
							show.setText("来自服务器的数据:" + line.toString());
							// 关闭输入流、socket
							br.close();
							s.close();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}.start();
			}
		});

	}
}