package com.example.activityforresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	Button bn;
	EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bn = (Button)findViewById(R.id.button1);
		et = (EditText)findViewById(R.id.editText1);
		bn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode ==0 && resultCode ==0) {
			Bundle bundle = data.getExtras();
			String resultCity = bundle.getString("city");
			et.setText(resultCity);
		}
	}
}