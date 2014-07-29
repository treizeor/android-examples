package com.example.eventqs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.btn);
		button.setOnClickListener(new MyClickListener());
	}

	class MyClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText editText = (EditText) findViewById(R.id.editText);
			editText.setText("按钮被单击了");
		}

	}
}