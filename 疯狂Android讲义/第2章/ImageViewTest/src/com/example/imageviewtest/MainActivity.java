package com.example.imageviewtest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	int[] images = new int[] { R.drawable.img1, R.drawable.img2,
			R.drawable.img3, R.drawable.img4, };

	int currentImage = 2;
	private int alpha = 255;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button btn1 = (Button) findViewById(R.id.btn1);
		final Button btn2 = (Button) findViewById(R.id.btn2);
		final Button btn3 = (Button) findViewById(R.id.btn3);
		final ImageView imgv1 = (ImageView) findViewById(R.id.image1);
		final ImageView imgv2 = (ImageView) findViewById(R.id.image2);

		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imgv1.setImageResource(images[++currentImage % images.length]);
			}
		});

		View.OnClickListener listener = new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.btn1:
					alpha += 20;
					break;
				case R.id.btn2:
					alpha -= 20;
					break;
				}
				if (alpha > 255) {
					alpha = 255;
				} else if (alpha < 0) {
					alpha = 0;
				}
				imgv1.setAlpha(alpha);
			}
		};
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);

		imgv1.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				BitmapDrawable bitmapDrawable = (BitmapDrawable) imgv1
						.getDrawable();
				Bitmap bitmap = bitmapDrawable.getBitmap();
				double scale = bitmap.getWidth() / 320.0;
				int x = (int) (event.getX() * scale);
				int y = (int) (event.getY() * scale);
				if (x + 120 > bitmap.getWidth()) {
					x = bitmap.getWidth() - 120;

				}
				if (y + 120 > bitmap.getHeight()) {
					y = bitmap.getHeight() - 120;
				}

				imgv2.setImageBitmap(Bitmap
						.createBitmap(bitmap, x, y, 120, 120));
				imgv2.setAlpha(alpha);
				return false;
			}
		});

	}

}