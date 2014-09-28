package com.example.firstprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class FirstProvider extends ContentProvider {

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		System.out.println("---onCreate----");
		return true;
	}

	// 实现查询方法，返回查询得到的Cursor
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		System.out.println("---query----");
		System.out.println("---query--selection参数为--" + selection);
		return null;
	}

	// 该方法的返回值代表了该ContentProvider所提供数据的MINE类型
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	// 实现插入的方法，返回新插入记录的Uri
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		System.out.println(uri + "---insert----");
		System.out.println("---values----:" + values);
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		System.out.println(uri + "---delete----:" + selection);
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		System.out.println(uri + "---update----:" + selection + "--values:"
				+ selection);
		return 0;
	}

}
