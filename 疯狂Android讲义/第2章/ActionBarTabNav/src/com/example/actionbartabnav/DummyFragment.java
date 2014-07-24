package com.example.actionbartabnav;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DummyFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TextView textView = new TextView(getActivity());
		textView.setGravity(Gravity.START);
		// 获取创建该Fragment是传入的参数Bundle
		Bundle args = getArguments();
		textView.setText(args.getString(ARG_SECTION_NUMBER));
		textView.setTextSize(30);
		return textView;
	}
}
