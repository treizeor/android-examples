package com.example.baidumaptest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Gradient;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

public class MainActivity extends Activity {

	MapView mMapView = null;
	BaiduMap mBaiduMap = null;
	Marker marker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件前初始化context信息，需要在setContentView前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.map_type_satellite:
			// 卫星地图
			mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.set_traffic_enable:
			// 交通图
			mBaiduMap.setTrafficEnabled(true);
			break;
		case R.id.set_baiduheatmap_enable:
			// 百度城市热力图
			mBaiduMap.setBaiduHeatMapEnabled(true);
			break;
		case R.id.marker_reset:
			if (marker != null) {
				marker.remove();
			}
			// 定义Marker坐标点
			LatLng point = new LatLng(39.963175, 116.400244);
			// 构建Marker图标
			BitmapDescriptor bitmap = BitmapDescriptorFactory
					.fromResource(R.drawable.icon_marka);
			// 构建MarkerOption，用于在地图上添加Marker
			OverlayOptions option = new MarkerOptions().position(point) // 设置marker位置
					.icon(bitmap) // 设置marker图标
					.zIndex(9) // 设置marker所在层级
					.draggable(true); // 设置可手势拖拽
			// 在地图上添加Marker,并显示
			marker = (Marker) mBaiduMap.addOverlay(option);

			mBaiduMap
					.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

						@Override
						public boolean onMarkerClick(Marker marker) {
							showPopOptions(marker.getPosition());
							return false;
						}
					});

			// 调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽到监听
			mBaiduMap
					.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {

						@Override
						public void onMarkerDragStart(Marker marker) {
							// TODO Auto-generated method stub
							System.out.println("--拖拽开始--");
							Toast.makeText(getApplicationContext(), "拖拽开始",
									Toast.LENGTH_LONG).show();
						}

						@Override
						public void onMarkerDragEnd(Marker marker) {
							// TODO Auto-generated method stub
							System.out.println("--拖拽结束--");

						}

						@Override
						public void onMarkerDrag(Marker marker) {
							// TODO Auto-generated method stub
							System.out.println("--拖拽中--");
						}
					});
			break;
		case R.id.marker_clear:
			// 移除标注物
			marker.remove();
			break;
		case R.id.polygon_options:
			// 定义多边形的五个顶点
			LatLng pt1 = new LatLng(39.93923, 116.357428);
			LatLng pt2 = new LatLng(39.91923, 116.327428);
			LatLng pt3 = new LatLng(39.89923, 116.347428);
			LatLng pt4 = new LatLng(39.89923, 116.367428);
			LatLng pt5 = new LatLng(39.91923, 116.387428);
			List<LatLng> pts = new ArrayList<LatLng>();
			pts.add(pt1);
			pts.add(pt2);
			pts.add(pt3);
			pts.add(pt4);
			pts.add(pt5);
			// 构建多边形的option对象
			OverlayOptions polygonOptions = new PolygonOptions().points(pts)
					.stroke(new Stroke(5, 0xAA00FF00)).fillColor(0xAAFF0000);
			// 在地图上显示多边形
			mBaiduMap.addOverlay(polygonOptions);
			break;
		case R.id.text_options:
			LatLng textLatLng = new LatLng(39.86923, 116.397428);
			OverlayOptions textOptions = new TextOptions().bgColor(0xAAFFFF00)
					.text("TREIZE到此一游").fontColor(0xFFFF0000).fontSize(30)
					.position(textLatLng).rotate(-45); // 45度的忧桑:)
			mBaiduMap.addOverlay(textOptions);
			break;
		case R.id.pop_options:
			// 定义用于显示该InfoWindow的坐标点
			LatLng pt = new LatLng(39.86923, 116.397428);
			showPopOptions(pt);
			break;
		case R.id.groud_overlay:
			// 定义Ground的显示地理范围
			LatLng southwest = new LatLng(39.92235, 116.380338);
			LatLng northeast = new LatLng(39.947246, 116.414977);
			LatLngBounds bounds = new LatLngBounds.Builder().include(northeast)
					.include(southwest).build();
			// 定义Ground显示的图片
			BitmapDescriptor overlay = BitmapDescriptorFactory
					.fromResource(R.drawable.ground_overlay);
			OverlayOptions overlayOptions = new GroundOverlayOptions()
					.positionFromBounds(bounds).image(overlay)
					.transparency(0.8f);
			mBaiduMap.addOverlay(overlayOptions);
			break;
		case R.id.baidumap_clear:
			mBaiduMap.clear();
			break;

		// 构建自有热力图
		case R.id.set_self_heatmap:
			// 设置渐变颜色值
			int[] DEFAULT_GRADIENT_COLORS = { Color.rgb(102, 225, 0),
					Color.rgb(255, 0, 0) };
			// 设置渐变颜色起始值
			float[] DEFAULT_GRADIENT_START_POINTS = { 0.2f, 1f };
			// 构造颜色渐变对象
			Gradient gradient = new Gradient(DEFAULT_GRADIENT_COLORS,
					DEFAULT_GRADIENT_START_POINTS);
			// 准备数据
			// 以下数据为随机生成地理位置点，开发者根据自己的实际业务，传入自有位置数据即可
			List<LatLng> randomLatLngs = new ArrayList<LatLng>();
			Random random = new Random();
			for (int i = 0; i < 500; i++) {
				int rlat = random.nextInt(370000);
				int rlng = random.nextInt(370000);
				int lat = 39780000 + rlat;
				int lng = 116220000 + rlng;
				LatLng latLng = new LatLng(lat / 1E6, lng / 1E6);
				randomLatLngs.add(latLng);
			}
			// 添加、显示热力图
			HeatMap heatMap = new HeatMap.Builder().data(randomLatLngs)
					.gradient(gradient).build();
			mBaiduMap.addHeatMap(heatMap);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void showPopOptions(LatLng pt) {
		// 创建InfoWindow展示的View
		Button button = new Button(getApplicationContext());
		button.setText("弹出窗");
		button.setTextColor(0xFFFF0000);
		button.setBackgroundResource(R.drawable.popup);
		// 定义用于显示该InfoWindow的坐标点
		// LatLng pt = new LatLng(39.86923, 116.397428);
		// 创建InfoWindow，传入View，坐标，偏移量
		InfoWindow mInfoWindow = new InfoWindow(button, pt, -47);
		// 显示InfoWindow
		mBaiduMap.showInfoWindow(mInfoWindow);

	}
}
