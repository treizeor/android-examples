package com.example.captureimage;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	SurfaceView sView;
	SurfaceHolder sHolder;
	int screenWidth, screenHeight;
	// 定义系统所用的照相机
	Camera camera;
	// 是否在预览中
	boolean isPreview = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		// 获取窗口管理器
		WindowManager wm = getWindowManager();
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		// 获取屏幕宽和高
		display.getMetrics(metrics);
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
		// 获取SurfaceView组件
		sView = (SurfaceView) findViewById(R.id.surfaceView1);
		// 设置该Surface不需要自己维护缓冲区 (新版本中已经不需要，Android自动选择SurfaceView缓冲区管理方式
		// sView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// 获得SurfaceHolder
		sHolder = sView.getHolder();
		// 为sHolder添加一个回调监听器
		sHolder.addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				// 如果camera不为null 释放摄像头
				if (camera != null) {
					if (isPreview) {
						camera.stopPreview();
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				// 打开摄像头
				initCamera();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void initCamera() {
		// TODO Auto-generated method stub
		if (!isPreview) {
			// 此处默认打开后置摄像头
			// 通过传入参数可打开前置摄像头
			camera = Camera.open(0);
			camera.setDisplayOrientation(90);
		}
		if (camera != null && !isPreview) {
			try {
				Camera.Parameters parameters = camera.getParameters();
				// 设置预览照片的大小
				parameters.setPreviewSize(screenWidth, screenHeight);
				// 设置预览照片时每秒显示多少帧的最小和最大值
				parameters.setPreviewFpsRange(4, 10);
				// 设置图片格式
				parameters.setPictureFormat(ImageFormat.JPEG);
				// 设置JPEG照片质量
				parameters.set("jpeg-quality", 85);
				// 设置照片的大小
				parameters.setPictureSize(screenWidth, screenHeight);
				// 通过SurfaceView显示取景画面
				camera.setPreviewDisplay(sHolder);
				// 开始预览
				camera.startPreview();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			isPreview = true;
		}
	}

	public void capture(View view) {
		if (camera != null) {
			// 控制摄像头对焦后才拍照
			camera.autoFocus(autoFocusCallback);
		}
	}

	AutoFocusCallback autoFocusCallback = new AutoFocusCallback() {

		@Override
		public void onAutoFocus(boolean success, Camera camera) {
			// TODO Auto-generated method stub
			if (success) {
				// takePicture()方法需要传入三个监听器参数
				// 第一个监听器：当用户按下快门时激发该监听器
				// 第二个监听器：当相机获取原始照片时激发该监听器
				// 第三个监听器：当相机获取JPG照片时激发该监听器
				camera.takePicture(new ShutterCallback() {

					@Override
					public void onShutter() {
						// TODO Auto-generated method stub

					}
				}, new PictureCallback() {

					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						// TODO Auto-generated method stub

					}
				}, myJpegCallBack);
			}
		}
	};

	PictureCallback myJpegCallBack = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// TODO Auto-generated method stub
			// 根据拍照所得的数据创建位图
			final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
					data.length);
			View saveDialog = getLayoutInflater().inflate(R.layout.save, null);
			final EditText photoName = (EditText) saveDialog
					.findViewById(R.id.photo_name);
			ImageView show = (ImageView) saveDialog.findViewById(R.id.show);
			// 显示刚刚拍得到的照片
			show.setImageBitmap(bm);
			// 使用对话框显示saveDialog组件
			new AlertDialog.Builder(MainActivity.this).setView(saveDialog)
					.setPositiveButton("保存", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							// 创建一个位于SD卡上的文件
							File file = new File(Environment
									.getExternalStorageDirectory(), photoName
									.getText().toString() + ".jpg");
							FileOutputStream outputStream = null;
							try {
								outputStream = new FileOutputStream(file);
								// 把位图输出到指定文件中
								bm.compress(CompressFormat.JPEG, 100,
										outputStream);
								outputStream.close();
								Toast.makeText(
										MainActivity.this,
										"照片保存到了:"
												+ photoName.getText()
														.toString() + ".jpg",
										Toast.LENGTH_LONG).show();
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}).setNegativeButton("取消", null).show();
			// 重新浏览
			camera.stopPreview();
			camera.startPreview();
			isPreview = true;
		}
	};
}