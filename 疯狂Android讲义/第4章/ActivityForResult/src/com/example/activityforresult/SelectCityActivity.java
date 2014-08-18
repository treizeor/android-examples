package com.example.activityforresult;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectCityActivity extends ExpandableListActivity {

	private String[] provinces = new String[] { "广东", "广西", "湖南", };
	private String[][] cities = new String[][] { { "广州", "深圳", "珠海", "湛江" },
			{ "桂林", "柳州", "南宁", "北海" }, { "长沙", "岳阳", "衡阳", "株洲" } };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
			
			private TextView getTextView(){
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
				TextView textView = new TextView(SelectCityActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}
			
			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout ll = new LinearLayout(SelectCityActivity.this);
				ll.setOrientation(0);
				ImageView logo = new ImageView(SelectCityActivity.this);
				logo.setImageResource(R.drawable.ic_launcher);
				ll.addView(logo);
				TextView textView = getTextView();
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);
				return ll;
			}
			
			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}
			
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return provinces.length;
			}
			
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return provinces[groupPosition];
			}
			
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return cities[groupPosition].length;
			}
			
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				TextView textView = getTextView();
				textView.setText(getChild(groupPosition, childPosition).toString());
				return textView;
			}
			
			
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;
			}
			
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return cities[groupPosition][childPosition];
			}
		};
		
		setListAdapter(adapter);
		getExpandableListView().setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Intent intent = getIntent();
				intent.putExtra("city", cities[groupPosition][childPosition]);
				SelectCityActivity.this.setResult(0, intent);
				SelectCityActivity.this.finish();
				return false;
			}
		});
	}
}
