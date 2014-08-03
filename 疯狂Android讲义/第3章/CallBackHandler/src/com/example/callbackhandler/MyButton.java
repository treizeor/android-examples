package com.example.callbackhandler;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

public class MyButton extends Button {

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		super.onKeyDown(keyCode, event);
		System.out.println("-----------------");
		Log.v("--myButton--", "the onKeyDown in My Button");
		return true;
	}

}
