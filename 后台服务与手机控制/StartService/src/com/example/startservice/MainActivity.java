package com.example.startservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button startButton;
	private Button stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MainActivity", "into");
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startService);
        stopButton = (Button)findViewById(R.id.stopService);
        
        startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, CountService.class);
				startService(intent);
			}
		});
        
        stopButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, CountService.class);
				stopService(intent);
				Log.v("MainActivity", "stopService");
			}
		});
    }
    
    
    
//    @Override
//    protected void onDestroy() {
//    	// TODO Auto-generated method stub
//    	super.onDestroy();
//    	Intent intent = new Intent(MainActivity.this, CountService.class);
//    	stopService(intent);
//    	Log.v("MainActivity", "out");
//    }

}
