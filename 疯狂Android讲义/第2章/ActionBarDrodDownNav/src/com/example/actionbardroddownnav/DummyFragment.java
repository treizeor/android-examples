package com.example.actionbardroddownnav;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DummyFragment extends Fragment {
	
	public final static String ARG_SECTION_NUMBER = "section_number";
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TextView textView = new TextView(getActivity());
		Bundle args = getArguments();
		textView.setText(args.getInt(ARG_SECTION_NUMBER)+"");
		textView.setTextSize(30);
		return textView;
	}

}
