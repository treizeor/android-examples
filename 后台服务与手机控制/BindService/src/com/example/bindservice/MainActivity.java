package com.example.bindservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String tag = "MainActivity";
	private Button btnBind, btnUnbind, btnGetSrvState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(tag, "into");
        setContentView(R.layout.activity_main);
        btnBind = (Button) findViewById(R.id.btnBind);
        btnUnbind = (Button) findViewById(R.id.btnUnbind);
        btnGetSrvState = (Button) findViewById(R.id.btnGetSrvState);
        
        // 创建启动Service的Intent
        final Intent intent = new Intent();
        intent.setClass(MainActivity.this, CountService.class);
        
        btnBind.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// 绑定指定Service
				bindService(intent, conn, BIND_AUTO_CREATE);
			}
		});
        btnUnbind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 解除绑定Service
				unbindService(conn);
			}
		});
        
        btnGetSrvState.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Service Count is "+myBinder.getCount(), 0).show();
			}
		});
    }
    
    
    
    
    // 保持所启动Service的IBinder对象
    CountService.MyBinder myBinder;
    private ServiceConnection conn = new ServiceConnection() {
		// 当该Activity与Service断开连接时调用该方法
		public void onServiceDisconnected(ComponentName name) {
			Log.v(tag, "---Service DisConnected");
		}
		
		
		// 当该Activity与Service连接成功时候调用该方法
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v(tag, "---Service Connected");
			// 获取Service的onBind方法所放回的MyBinder对象
			myBinder = (CountService.MyBinder) service;
		}
	};
}
