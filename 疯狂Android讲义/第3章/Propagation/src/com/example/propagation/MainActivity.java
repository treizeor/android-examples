package com.example.propagation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button=(Button)findViewById(R.id.myButton);
		System.out.println("------->>");
		button.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == KeyEvent.ACTION_DOWN){
					Log.v("---->>Listener", "the onKeyDown in Listener");
					System.out.println("------->>");
				}
				return false;
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		super.onKeyDown(keyCode, event);
		Log.v("---->>Activity", "the onKeyDown in Activity");
		return false;
	}
}