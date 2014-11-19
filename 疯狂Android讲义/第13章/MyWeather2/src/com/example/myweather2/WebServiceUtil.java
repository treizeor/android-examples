package com.example.myweather2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebServiceUtil {
	// 定义 Web Service 的命名空间
	static final String SERVICE_NS = "http://WebXml.com.cn/";
	// 定义 Web Service 提供服务的URL
	static final String SERVICE_URL = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx";

	// 远程调用 Web Service 获取省份列表
	public static List<String> getProvinceList() {
		// 调用的方法
		final String methodName = "getRegionProvince";
		// 创建 HttpTransportSE 传输对象
		final HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		// 使用 SOAP1.1 协议创建 Envelop 对象
		final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// 实例化 SoapObject对象
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		envelope.bodyOut = soapObject;
		// 设置与 .NET 提供的Web Service保存较好的兼容性
		envelope.dotNet = true;
		FutureTask<List<String>> task = new FutureTask<List<String>>(
				new Callable<List<String>>() {

					@Override
					public List<String> call() throws Exception {
						// TODO Auto-generated method stub
						// 调用web service
						ht.call(SERVICE_NS + methodName, envelope);
						if (envelope.getResponse() != null) {
							// 获取服务器响应返回的SOAP消息
							SoapObject result = (SoapObject) envelope.bodyIn;
							SoapObject detail = (SoapObject) result
									.getProperty(methodName + "Result");
							// 解析服务器响应的SOAP消息
							return parseProvinceOrCity(detail);
						}
						return null;
					}

				});
		new Thread(task).start();
		try {
			return task.get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	// 根据省份获取城市列表
	public static List<String> getCityListByProvince(String province) {
		// 调用的方法
		final String methodName = "getSupportCityString";
		// 创建HttpTransportSE传输对象
		final HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		// 添加一个请求参数
		soapObject.addProperty("theRegionCode", province);
		// 创建Envelop对象
		final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = soapObject;
		envelope.dotNet = true;
		FutureTask<List<String>> task = new FutureTask<List<String>>(
				new Callable<List<String>>() {

					@Override
					public List<String> call() throws Exception {
						// TODO Auto-generated method stub
						ht.call(SERVICE_NS + methodName, envelope);
						SoapObject result = (SoapObject) envelope.bodyIn;
						SoapObject detail = (SoapObject) result
								.getProperty(methodName + "Result");
						return parseProvinceOrCity(detail);
					}
				});
		new Thread(task).start();
		try {
			return task.get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	private static List<String> parseProvinceOrCity(SoapObject detail) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < detail.getPropertyCount(); i++) {
			// 解析出每个省份或城市
			result.add(detail.getProperty(i).toString().split(",")[0]);
		}
		return result;
	}

	public static SoapObject getWeatherByCity(String cityName) {
		final String methodName = "getWeather";
		final HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
		ht.debug = true;
		final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
		soapObject.addProperty("theCityCode", cityName);
		envelope.bodyOut = soapObject;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(soapObject);
		FutureTask<SoapObject> task = new FutureTask<SoapObject>(
				new Callable<SoapObject>() {

					@Override
					public SoapObject call() throws Exception {
						// TODO Auto-generated method stub
						ht.call(SERVICE_NS + methodName, envelope);
						SoapObject result = (SoapObject) envelope.bodyIn;
						SoapObject detail = (SoapObject) result
								.getProperty(methodName + "Result");
						return detail;
					}
				});
		new Thread(task).start();
		try {
			return task.get();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
