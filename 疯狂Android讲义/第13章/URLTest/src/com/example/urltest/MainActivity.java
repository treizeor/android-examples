package com.example.urltest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView show;
	// 代表从网络上下载得到的图片
	Bitmap bitmap;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				show.setImageBitmap(bitmap);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (ImageView) findViewById(R.id.imageView1);
		new Thread() {
			public void run() {
				try {
					// 定义一个URL对象
					URL url = new URL(
							"http://www.joyen.net/article/soft0902/200909/2009090901110602.jpg");
					// 打开该URL对于的资源的输入流
					InputStream is = url.openStream();
					// 从InputStream中解析出图片
					bitmap = BitmapFactory.decodeStream(is);
					// 发生消息、通知UI组件显示该图片
					handler.sendEmptyMessage(0x123);
					is.close();

					// 下载图片
					// 再次打开URL对应的输入流
					is = url.openStream();
					OutputStream os = openFileOutput("Audrey.jpg",
							MODE_WORLD_READABLE);
					byte[] buff = new byte[1024];
					int hasRead = 0;
					// 将URL对应资源下载到本地
					while ((hasRead = is.read(buff)) > 0) {
						os.write(buff, 0, hasRead);
					}
					is.close();
					os.close();

				} catch (Exception e) {
					// TODO: handle exception
				}
			};
		}.start();
	}
}