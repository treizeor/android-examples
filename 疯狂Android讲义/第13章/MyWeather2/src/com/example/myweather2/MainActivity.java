package com.example.myweather2;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.R.integer;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Spinner provinceSpinner;
	private Spinner citySpinner;
	private ImageView todayWhIcon1;
	private ImageView todayWhIcon2;
	private ImageView tomorrowWhIcon1;
	private ImageView tomorrowWhIcon2;
	private ImageView afterdayWhIcon1;
	private ImageView afterdayWhIcon2;
	private TextView todayWhText, tomorrowWhText, afterdayWhText,
			textWhCurrent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		todayWhIcon1 = (ImageView) findViewById(R.id.todayWhIcon1);
		todayWhIcon2 = (ImageView) findViewById(R.id.todayWhIcon2);
		todayWhText = (TextView) findViewById(R.id.todayWhText);
		tomorrowWhIcon1 = (ImageView) findViewById(R.id.tomorrowWhIcon1);
		tomorrowWhIcon2 = (ImageView) findViewById(R.id.tomorrowWhIcon2);
		tomorrowWhText = (TextView) findViewById(R.id.tomorrowWhText);
		afterdayWhIcon1 = (ImageView) findViewById(R.id.afterdayWhIcon1);
		afterdayWhIcon2 = (ImageView) findViewById(R.id.afterdayWhIcon2);
		afterdayWhText = (TextView) findViewById(R.id.afterdayWhText);
		textWhCurrent = (TextView) findViewById(R.id.weatherCurrent);
		provinceSpinner = (Spinner) findViewById(R.id.province);
		citySpinner = (Spinner) findViewById(R.id.city);

		// 调用远程Web Service获取省份列表
		List<String> provinces = WebServiceUtil.getProvinceList();
		System.out.println("-----" + provinces);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, provinces);
		provinceSpinner.setAdapter(adapter);

		// 当省份Spinner的选项改变时
		provinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				List<String> cities = WebServiceUtil
						.getCityListByProvince(provinceSpinner
								.getSelectedItem().toString());
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						MainActivity.this,
						android.R.layout.simple_spinner_dropdown_item, cities);
				citySpinner.setAdapter(adapter);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// 当城市Spinner选项被选中时
		citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				showWeather(citySpinner.getSelectedItem().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void showWeather(String city) {
		/*
		 * // TODO Auto-generated method stub String weatherToday = null; String
		 * weatherTomorrow = null; String weatherAfterday = null; String
		 * weatherCurrent = null; int iconToday[] = new int[2]; int
		 * iconTomorrow[] = new int[2]; int iconAfterday[] = new int[2]; //
		 * 获取远程Web Service返回的对象 SoapObject detail =
		 * WebServiceUtil.getWeatherByCity(city); // 获取天气实况 weatherCurrent =
		 * detail.getProperty(4).toString(); // 解析今日天气 String date =
		 * detail.getProperty(7).toString(); weatherToday = "今天:" +
		 * date.split(" ")[0]; weatherToday = weatherToday + "\n天气:" +
		 * date.split(" ")[1]; weatherToday = weatherToday + "\n气温:" +
		 * detail.getProperty(8).toString(); weatherToday = weatherToday +
		 * "\n风力:" + detail.getProperty(9).toString() + "\n"; iconToday[0] =
		 * parseIcon(detail.getProperty(10).toString()); iconToday[1] =
		 * parseIcon(detail.getProperty(11).toString());
		 * 
		 * textWhCurrent.setText(weatherCurrent);
		 * todayWhText.setText(weatherToday);
		 * todayWhIcon1.setImageResource(iconToday[0]);
		 * todayWhIcon2.setImageResource(iconToday[1]);
		 */

		// ------------------------------------
		String weatherToday = null;
		String weatherTomorrow = null;
		String weatherAfterday = null;
		String weatherCurrent = null;
		int iconToday[] = new int[2];
		int iconTomorrow[] = new int[2];
		int iconAfterday[] = new int[2];
		// 获取远程Web Service返回的对象
		SoapObject detail = WebServiceUtil.getWeatherByCity(city);
		weatherCurrent = detail.getProperty(4).toString();
		System.out.println("-----" + weatherCurrent);
	}

	private int parseIcon(String strIcon) {
		// TODO Auto-generated method stub
		if (strIcon == "")
			return -1;
		if ("0.gif".equals(strIcon))
			return R.drawable.a_0;
		if ("1.gif".equals(strIcon))
			return R.drawable.a_1;
		if ("2.gif".equals(strIcon))
			return R.drawable.a_2;
		if ("3.gif".equals(strIcon))
			return R.drawable.a_3;
		if ("4.gif".equals(strIcon))
			return R.drawable.a_4;
		if ("5.gif".equals(strIcon))
			return R.drawable.a_5;
		if ("6.gif".equals(strIcon))
			return R.drawable.a_6;
		if ("7.gif".equals(strIcon))
			return R.drawable.a_7;
		if ("8.gif".equals(strIcon))
			return R.drawable.a_8;
		if ("9.gif".equals(strIcon))
			return R.drawable.a_9;
		if ("10.gif".equals(strIcon))
			return R.drawable.a_10;
		if ("11.gif".equals(strIcon))
			return R.drawable.a_11;
		if ("12.gif".equals(strIcon))
			return R.drawable.a_12;
		if ("13.gif".equals(strIcon))
			return R.drawable.a_13;
		if ("14.gif".equals(strIcon))
			return R.drawable.a_14;
		if ("15.gif".equals(strIcon))
			return R.drawable.a_15;
		if ("16.gif".equals(strIcon))
			return R.drawable.a_16;
		if ("17.gif".equals(strIcon))
			return R.drawable.a_17;
		if ("18.gif".equals(strIcon))
			return R.drawable.a_18;
		if ("19.gif".equals(strIcon))
			return R.drawable.a_19;
		if ("20.gif".equals(strIcon))
			return R.drawable.a_20;
		if ("21.gif".equals(strIcon))
			return R.drawable.a_21;
		if ("22.gif".equals(strIcon))
			return R.drawable.a_22;
		if ("23.gif".equals(strIcon))
			return R.drawable.a_23;
		if ("24.gif".equals(strIcon))
			return R.drawable.a_24;
		if ("25.gif".equals(strIcon))
			return R.drawable.a_25;
		if ("26.gif".equals(strIcon))
			return R.drawable.a_26;
		if ("27.gif".equals(strIcon))
			return R.drawable.a_27;
		if ("28.gif".equals(strIcon))
			return R.drawable.a_28;
		if ("29.gif".equals(strIcon))
			return R.drawable.a_29;
		if ("30.gif".equals(strIcon))
			return R.drawable.a_30;
		if ("31.gif".equals(strIcon))
			return R.drawable.a_31;
		return 0;
	}
}