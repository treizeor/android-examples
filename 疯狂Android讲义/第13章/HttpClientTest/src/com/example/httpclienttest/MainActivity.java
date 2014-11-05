package com.example.httpclienttest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView response;
	HttpClient httpClient;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x123) {
				response.append(msg.obj.toString() + "\n");
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		httpClient = new DefaultHttpClient();
		response = (TextView) findViewById(R.id.response);
	}

	public void accessSecret(View v) {
		response.setText("");
		new Thread() {
			public void run() {
				HttpGet get = new HttpGet(
						"http://192.168.3.102:8080/foo/secret.jsp");
				try {
					HttpResponse httpResponse = httpClient.execute(get);
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null) {
						BufferedReader br = new BufferedReader(
								new InputStreamReader(entity.getContent()));
						String line = null;

						while ((line = br.readLine()) != null) {
							Message msg = new Message();
							msg.what = 0x123;
							msg.obj = line;
							handler.sendMessage(msg);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			};
		}.start();
	}

	public void showLogin(View v) {
		final View loginDialog = getLayoutInflater().inflate(R.layout.login,
				null);
		new AlertDialog.Builder(MainActivity.this).setTitle("登录系统")
				.setView(loginDialog)
				.setPositiveButton("登录", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final String name = ((EditText) loginDialog
								.findViewById(R.id.editText1)).getText()
								.toString();
						final String pass = ((EditText) loginDialog
								.findViewById(R.id.editText2)).getText()
								.toString();
						new Thread() {
							public void run() {
								try {
									HttpPost post = new HttpPost(
											"http://192.168.3.102:8080/foo/login.jsp");
									List<NameValuePair> params = new ArrayList<NameValuePair>();
									params.add(new BasicNameValuePair("name",
											name));
									params.add(new BasicNameValuePair("pass",
											pass));
									post.setEntity(new UrlEncodedFormEntity(
											params, HTTP.UTF_8));
									HttpResponse response = httpClient
											.execute(post);
									if (response.getStatusLine()
											.getStatusCode() == 200) {
										String msg = EntityUtils
												.toString(response.getEntity());
										Looper.prepare();
										Toast.makeText(MainActivity.this, msg,
												0).show();
										Looper.loop();
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							};
						}.start();
					}
				}).setNegativeButton("取消", null).show();
	}
}