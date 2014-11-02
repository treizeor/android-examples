package com.example.multithreadclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText input;
	TextView show;
	Button send;
	Handler handler;
	// 定义与服务器通信的子线程
	ClientThread clientThread;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input = (EditText)findViewById(R.id.input);
		send = (Button)findViewById(R.id.send);
		show = (TextView)findViewById(R.id.show);
		
		handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				// 如果消息来自子线程
				if (msg.what == 0x123) {
					// 将读取的内容追加显示在文本框中
					show.append("\n"+msg.obj.toString());
				}
			};
		};
		clientThread = new ClientThread(handler);
		// 客户端启动ClientThread线程创建网络连接，读取来自服务器的数据 
		new Thread(clientThread).start();
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					//  当用户按下发送按钮后，将用户输入的数据封装成Message
					// 然后发送给子线程的Handler
					Message msg = new Message();
					msg.what = 0x345;
					msg.obj = input.getText().toString();
					clientThread.revHandler.sendMessage(msg);
					input.setText("");
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
}