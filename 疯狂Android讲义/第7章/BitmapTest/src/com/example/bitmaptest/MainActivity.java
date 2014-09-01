package com.example.bitmaptest;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	String[] images = null;
	AssetManager assets = null;
	int currentImg = 0;
	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imageView1);
		try {
			assets = getAssets();
			images = assets.list("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Button next = (Button) findViewById(R.id.button1);
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 如果发生数组越界
				if (currentImg >= images.length) {
					currentImg = 0;
				}
				// 非图片跳过
				while (!images[currentImg].endsWith(".png")
						&& !images[currentImg].endsWith(".jpg")
						&& !images[currentImg].endsWith(".gif")) {
					currentImg++;
					// 如果发生数组越界
					if (currentImg >= images.length) {
						currentImg = 0;
					}
				}

				InputStream assetFile = null;
				try {
					// 打开指定资源对应的输入流
					assetFile = assets.open(images[currentImg++]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView
						.getDrawable();
				// 如果图片还未回收，先强制回收改图片
				if (bitmapDrawable != null
						&& !bitmapDrawable.getBitmap().isRecycled()) {
					bitmapDrawable.getBitmap().recycle();
				}
				imageView.setImageBitmap(BitmapFactory.decodeStream(assetFile));
			}
		});
	}
}