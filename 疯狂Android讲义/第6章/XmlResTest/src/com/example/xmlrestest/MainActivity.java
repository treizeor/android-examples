package com.example.xmlrestest;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 根据XML资源的ID获取解析该资源的解析器
				// XmlResourceParser是XmlPullparser的子类
				XmlResourceParser xrp = getResources().getXml(R.xml.books);
				try {
					StringBuilder sb = new StringBuilder();
					while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
						if (xrp.getEventType() == XmlResourceParser.START_TAG) {
							String tagName = xrp.getName();
							if (tagName.equals("book")) {
								String bookPrice = xrp.getAttributeValue(null, "price");
								sb.append("价格:");
								sb.append(bookPrice);
								String bookTime = xrp.getAttributeValue(null, "出版日期");
								sb.append("   出版日期:");
								sb.append(bookTime);
								sb.append("  书名:");
								sb.append(xrp.nextText());
								
							}
							sb.append("\n");
						}
						xrp.next();
					}
					TextView tv = (TextView)findViewById(R.id.textView1);
					tv.setText(sb.toString());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
}