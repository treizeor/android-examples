package com.example.otheractivity;

import java.util.List;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Button;
import android.widget.Toast;

public class PreferenceActivityTest extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 该方法为该界面设置一个标题按钮
		if (hasHeaders()) {
			Button button = new Button(this);
			button.setText("设置操作");
			// 将该按钮添加到界面上
			setListFooter(button);
		}
	}
	
	// 重写该方法，负责加载页面布局文件
	@Override
	public void onBuildHeaders(List<Header> target) {
		// TODO Auto-generated method stub
		loadHeadersFromResource(R.xml.preference_headers, target);
	}
	
	public static class Prefs1Fragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}
	public static class Prefs2Fragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.display_prefs);
			String website = getArguments().getString("website");
			Toast.makeText(getActivity(), website, Toast.LENGTH_LONG).show();
		}
	}
	
//  // 4.4中强制要求实现该方法，由于没有4.4之前的模拟器，不知道4.4之前可不可运行，目前点击Fragment时崩溃
//	protected boolean isValidFragment(String fragmentName) {
//		return super.isValidFragment(fragmentName);
//	}

}
