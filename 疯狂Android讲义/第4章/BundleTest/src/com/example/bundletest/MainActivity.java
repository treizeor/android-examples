package com.example.bundletest;

import android.app.Activity;
import android.content.Intent;
import android.net.MailTo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button commit = (Button)findViewById(R.id.commit);
		commit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText name=(EditText)findViewById(R.id.name);
				EditText passwd=(EditText)findViewById(R.id.passwd);
				RadioButton male = (RadioButton)findViewById(R.id.male);
				//RadioButton female = (RadioButton)findViewById(R.id.female);
				
				String gender = male.isChecked()?"男":"女";
				Person p = new Person(name.getText().toString(), passwd.getText().toString(), gender);
				Bundle data = new Bundle();
				data.putSerializable("person", p);
				Intent intent = new Intent(MainActivity.this, ResultActivity.class);
				intent.putExtras(data);
				startActivity(intent);
			}
		});
	}
}

