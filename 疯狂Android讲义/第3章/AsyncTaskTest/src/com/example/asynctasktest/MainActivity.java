package com.example.asynctasktest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText show;
	private Button download;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = (EditText) findViewById(R.id.editText1);
		download = (Button) findViewById(R.id.button1);
		
		download.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DownTask task = new DownTask(MainActivity.this);
				try {
					task.execute(new URL("http://www.baidu.com"));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	class DownTask extends AsyncTask<URL, integer, String> {
		ProgressDialog pgDialog;
		// 已读取行数
		int hasRead = 0;
		Context mContext;

		public DownTask(Context ctx) {
			// TODO Auto-generated constructor stub
			this.mContext = ctx;
		}

		@Override
		protected String doInBackground(URL... params) {
			// TODO Auto-generated method stub
			StringBuilder sb = new StringBuilder();
			try {
				URLConnection conn = params[0].openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
					hasRead++;
//					publishProgress(hasRead);
				}
				return sb.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			show.setText(result);
			pgDialog.dismiss();
		}
		
		@Override
		protected void onPreExecute() {
			pgDialog = new ProgressDialog(mContext);
			pgDialog.setTitle("Downloading...");
			pgDialog.setMessage("Downloading, Keep waiting...");
			pgDialog.setCancelable(false);
			pgDialog.setMax(202);
			pgDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pgDialog.setIndeterminate(false);
			pgDialog.show();
		}
		
		@Override
		protected void onProgressUpdate(integer... values) {
			// TODO Auto-generated method stub
			show.setText("已经读取了【"+ values[0] + "】行!");
			pgDialog.setProgress(Integer.parseInt(values[0].toString()));
		}

	}
}