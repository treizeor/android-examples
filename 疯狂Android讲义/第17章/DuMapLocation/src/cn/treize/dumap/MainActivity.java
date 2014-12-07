package cn.treize.dumap;

import java.util.Timer;
import java.util.TimerTask;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public LocationClient mLocationClient;
	public BDLocationListener myListener = new MyLocationListener();
	public NotifyListener mNotifyListener;

	private EditText showLocation;
	private Button startNotify, stopNotify;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showLocation = (EditText) findViewById(R.id.showLocation);
		startNotify = (Button) findViewById(R.id.startNotify);
		stopNotify = (Button) findViewById(R.id.stopNotify);
		mLocationClient = new LocationClient(getApplicationContext());

		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy); // 设置定位模式
		option.setCoorType("bd0911"); // 返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(5000); // 发起定位请求时间间隔为5000ms
		option.setIsNeedAddress(true); // 返回的结果包含地址信息
		option.setNeedDeviceDirect(true); // 返回定位结果包含手机朝向
		option.setOpenGps(true); // 设置打开GPS，默认是不打开的
		mLocationClient.setLocOption(option);

		// 注册监听器
		mLocationClient.registerLocationListener(myListener);
		mLocationClient.start();

		new Thread() {
			public void run() {
				new Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 每隔3秒请求一次位置信息
						mLocationClient.requestLocation();
					}
				}, 0, 300);
			};
		}.start();

		// 位置提醒
		startNotify.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mNotifyListener = new NotifyListener();
				// 4个参数代表要位置提醒的点的坐标，具体含义依次为：纬度，经度，距离范围，坐标系类型(gcj02,gps,bd09,bd09ll)
				mNotifyListener.SetNotifyLocation(23.0206, 113.0960, 3000,
						"gcj02");
				// 注册位置监听提醒
				mLocationClient.registerNotify(mNotifyListener);
			}
		});

		stopNotify.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mLocationClient.removeNotifyEvent(mNotifyListener);
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mLocationClient.stop();
		mLocationClient.unRegisterLocationListener(myListener);
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			if (location == null) {
				return;
			}
			StringBuilder sb = new StringBuilder();
			sb.append("time: ");
			sb.append(location.getTime());
			sb.append("\nerr code: ");
			sb.append(location.getLocType());
			sb.append("\nlatitude: ");
			sb.append(location.getLatitude());
			sb.append("\nlongitude: ");
			sb.append(location.getLongitude());
			sb.append("\nradius: ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed: ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite: ");
				sb.append(location.getSatelliteNumber());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr: ");
				sb.append(location.getAddrStr()); // 获取反地理编码地址
				sb.append("\nprovince: ");
				sb.append(location.getProvince());
				sb.append("\ncity: ");
				sb.append(location.getCity());
				sb.append("\ndistrict: ");
				sb.append(location.getDistrict());
			}
			// logMsg(sb.toString());
			showLocation.setText(sb.toString());
		}
	}

	public class NotifyListener extends BDNotifyListener {
		@Override
		public void onNotify(BDLocation location, float distance) {
			// TODO Auto-generated method stub
			Toast.makeText(
					getApplicationContext(),
					"距离" + location.getAddrStr() + String.valueOf(distance)
							+ "米", Toast.LENGTH_LONG).show();
		}
	}
}