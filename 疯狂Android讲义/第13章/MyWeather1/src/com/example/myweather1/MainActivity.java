package com.example.myweather1;

import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText cityText;
	private Button serach;
	private TextView resultView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cityText = (EditText) findViewById(R.id.cityName);
		serach = (Button) findViewById(R.id.query);
		resultView = (TextView) findViewById(R.id.show);
		serach.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String cityName = cityText.getText().toString();
				String wsdlUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
				String method = "getWeatherbyCityName";// WebService 的调用方法
				SoapObject result = (SoapObject) SOAPUtil.doTransport(wsdlUrl,
						method, cityName);
				System.out.println("result:" + result);
				resultView.setText(result.getProperty(10).toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

// ------------------------------
/*
 * import org.ksoap2.SoapEnvelope; import org.ksoap2.serialization.SoapObject;
 * import org.ksoap2.serialization.SoapSerializationEnvelope; import
 * org.ksoap2.transport.HttpTransportSE;
 * 
 * import android.app.Activity; import android.os.AsyncTask; import
 * android.os.Bundle; import android.view.View; import android.widget.Button;
 * import android.widget.EditText; import android.widget.TextView;
 * 
 * public class MainActivity extends Activity {
 * 
 * private static final String NAMESPACE = "http://WebXml.com.cn/"; private
 * static String URL =
 * "http://www.webxml.com.cn/webservices/weatherwebservice.asmx"; private static
 * final String METHOD_NAME = "getWeatherbyCityName"; // 设置查询接口参数 private static
 * String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyC-ityName"; private
 * String weatherToday; private Button okButton; private SoapObject detail;
 * private EditText cityNameText; private TextView cityMsgView;
 * 
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
 * okButton = (Button) findViewById(R.id.query); cityNameText = (EditText)
 * findViewById(R.id.cityName); cityMsgView = (TextView)
 * findViewById(R.id.show);
 * 
 * okButton.setOnClickListener(new View.OnClickListener() {
 * 
 * @Override public void onClick(View v) { // TODO Auto-generated method stub
 * new showWeatherAsyncTask().execute(); } }); }
 * 
 * class showWeatherAsyncTask extends AsyncTask<String, Integer, String> {
 * 
 * @Override protected String doInBackground(String... params) { // TODO
 * Auto-generated method stub showWeather(); return null; }
 * 
 * private void showWeather() { // TODO Auto-generated method stub String city =
 * cityNameText.getText().toString().trim(); if (!city.isEmpty()) {
 * getWeather(city); } }
 * 
 * private void getWeather(String city) { // TODO Auto-generated method stub try
 * { SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
 * soapObject.addProperty("theCityName", city); HttpTransportSE ht = new
 * HttpTransportSE(URL); ht.debug = true; SoapSerializationEnvelope envelope =
 * new SoapSerializationEnvelope( SoapEnvelope.VER11); envelope.bodyOut =
 * soapObject; envelope.dotNet = true; envelope.setOutputSoapObject(soapObject);
 * ht.call(SOAP_ACTION, envelope); SoapObject result = (SoapObject)
 * envelope.bodyIn; detail = (SoapObject) result
 * .getProperty("getWeatherbyCityNameResult"); System.out.println("detail" +
 * detail); parseWeather(detail); } catch (Exception e) { // TODO: handle
 * exception } }
 * 
 * }
 * 
 * public void parseWeather(SoapObject detail) { // TODO Auto-generated method
 * stub String date = detail.getProperty(6).toString(); weatherToday = "今天：" +
 * date.split(" ")[0]; weatherToday = weatherToday + " 天气：" +
 * date.split(" ")[1]; weatherToday = weatherToday + " 气温：" +
 * detail.getProperty(5).toString(); weatherToday = weatherToday + " 风力：" +
 * detail.getProperty(7).toString() + " "; System.out.println("weatherToday is "
 * + weatherToday); // 显示到cityMsgView控件上 cityMsgView.setText(weatherToday); }
 * 
 * 
 * }
 */