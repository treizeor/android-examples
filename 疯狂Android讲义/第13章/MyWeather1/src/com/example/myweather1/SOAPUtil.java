package com.example.myweather1;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class SOAPUtil {

	public static Object doTransport(final String wsdlUrl,
			final String webMethod, String cityName) {
		String nameSpace = "http://WebXml.com.cn/"; // WebService 的命名空间
		SoapObject soapObject = new SoapObject(nameSpace, webMethod);
		soapObject.addProperty("theCityName", cityName);// 设置调用方法的参数值
		System.out.println(cityName);
		// 生成调用Webservice方法的SOAP请求信息
		SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		soapSerializationEnvelope.bodyOut = soapObject;
		soapSerializationEnvelope.dotNet = true;
		soapSerializationEnvelope.setOutputSoapObject(soapObject);
		// 创建HttpTransportsSE对象。
		HttpTransportSE httpTransportSE = new HttpTransportSE(wsdlUrl);
		httpTransportSE.debug = true;
		String SOAP_ACTION = "http://WebXml.com.cn/" + webMethod;
		System.out.println(SOAP_ACTION);

		try {
			// 使用call方法调用WebService方法
			httpTransportSE.call(SOAP_ACTION, soapSerializationEnvelope);
			// 获得WebService方法的返回结果
			if (soapSerializationEnvelope.getResponse() != null) {
				SoapObject result = (SoapObject) soapSerializationEnvelope
						.getResponse();
				System.out.println("result:" + result);
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
