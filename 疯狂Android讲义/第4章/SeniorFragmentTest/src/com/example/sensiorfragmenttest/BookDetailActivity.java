package com.example.sensiorfragmenttest;

import com.example.sensiorfragmenttest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class BookDetailActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_book_detail);
		// 将ActionBar上的应用图标替换成可点击的按钮
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if (savedInstanceState == null) {
			BookDetailFragment fragment = new BookDetailFragment();
			Bundle arguments = new Bundle();
			arguments.putInt(BookDetailFragment.ITEM_ID, getIntent()
					.getIntExtra(BookDetailFragment.ITEM_ID, 0));
			fragment.setArguments(arguments);
			getFragmentManager().beginTransaction()
					.add(R.id.book_detail_container, fragment).commit();
		}
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			Intent intent = new Intent(this, BookListActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
