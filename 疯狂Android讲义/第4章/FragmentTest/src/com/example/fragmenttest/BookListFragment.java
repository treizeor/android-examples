package com.example.fragmenttest;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fragmenttest.model.BookContent;

public class BookListFragment extends ListFragment {

	private Callbacks mCallbacks;

	// 定义一个回调接口，该Fragment所在Activity必须实现该接口
	// 该Fragment通过该接口与它所在的Activity交互
	public interface Callbacks {
		public void onItemSelected(Integer id);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, BookContent.ITEMS));
	}

	@Override
	// 当Fragment被添加显示到Activity时，回调该方法
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"BookListFragment所在Activity必须实现Callbacks接口");
		}
		mCallbacks = (Callbacks) activity;
	}

	// 当Fragment从它所属的Activity中被删时回调该方法
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		mCallbacks = null;
	}

	// 当用户单击某项列表时激发该回调方法
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		// 激发mCallbacks的onItemSelected方法
		mCallbacks.onItemSelected(BookContent.ITEMS.get(position).id);
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}
}
