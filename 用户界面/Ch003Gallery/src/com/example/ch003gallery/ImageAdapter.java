package com.example.ch003gallery;

import android.content.Context;
import android.graphics.Matrix.ScaleToFit;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private int[] imgResId = new int[] {R.drawable.img1, R.drawable.img2, R.drawable.img3, };
	
	public ImageAdapter(Context context){
		super();
		this.context = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imgResId.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return imgResId[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(imgResId[position]);
//		imageView.setScaleType(imageView.SCALE_X);
		
		
		return imageView;
	}

}
