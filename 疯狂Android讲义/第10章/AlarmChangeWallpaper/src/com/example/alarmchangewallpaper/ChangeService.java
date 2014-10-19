package com.example.alarmchangewallpaper;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

public class ChangeService extends Service {

	int[] wallpapers = new int[] { R.drawable.img1, R.drawable.img2,
			R.drawable.img3, R.drawable.img4, };

	WallpaperManager wManager;

	int current = 0;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if (current > 4) {
			current = 0;
		}
		try {
			wManager.setResource(wallpapers[current++]);
			System.out.println("-----"+current+"----");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return START_STICKY;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		wManager = WallpaperManager.getInstance(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
