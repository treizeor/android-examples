package com.example.numberpicker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	NumberPicker np1, np2;
	//定义最低价最高级的初始值
	int minPrice = 25, maxPrice = 75;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		np1 = (NumberPicker)findViewById(R.id.np1);
		np2 = (NumberPicker)findViewById(R.id.np2);
		np1.setMinValue(10);
		np1.setMaxValue(50);
		np2.setMinValue(50);
		np2.setMaxValue(100);
		
		np1.setValue(minPrice);
		np2.setValue(maxPrice);
		np1.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "oldVal:"+oldVal+"  newVal:"+newVal, 0).show();
			}
		});
		
		np2.setOnValueChangedListener(new OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this,
						"oldVal:" + oldVal + "  newVal:" + newVal, 0).show();
				
			}
		});
	}
}