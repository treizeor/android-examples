package com.example.sensiorfragmenttest;


import com.example.sensiorfragmenttest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BookListActivity extends Activity implements
		BookListFragment.Callbacks {

	private boolean mTwoPane; // 定义一个旗标，用于标识是否支持大屏幕

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_list);
		if (findViewById(R.id.book_detail_container) != null) {
			mTwoPane = true;
			((BookListFragment) getFragmentManager().findFragmentById(
					R.id.book_list)).setActivateOnItemClick(true);
		}
	}

	@Override
	public void onItemSelected(Integer id) {
		// TODO Auto-generated method stub
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putInt(BookDetailFragment.ITEM_ID, id);
			BookDetailFragment fragment = new BookDetailFragment();
			fragment.setArguments(arguments);
			getFragmentManager().beginTransaction()
					.replace(R.id.book_detail_container, fragment).commit();
		} else {
			Intent detailIntent = new Intent();
			detailIntent.putExtra(BookDetailFragment.ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
