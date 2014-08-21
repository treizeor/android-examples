package com.example.sensiorfragmenttest;

import android.app.Activity;
import android.os.Bundle;

public class SelectBookActivity extends Activity implements
		BookListFragment.Callbacks {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books_twopane);
	}

	@Override
	public void onItemSelected(Integer id) {
		// TODO Auto-generated method stub
		Bundle arguments = new Bundle();
		arguments.putInt(BookDetailFragment.ITEM_ID, id);
		BookDetailFragment fragment = new BookDetailFragment();
		fragment.setArguments(arguments);
		getFragmentManager().beginTransaction()
				.replace(R.id.book_detail_container, fragment).commit();
	}
}
