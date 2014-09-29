package com.example.dictprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DictProvider extends ContentProvider {

	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int WORDS = 1;
	private static final int WORD = 2;
	private MyDatabaseHelper dbOpenHelper;

	static {
		// 为UriMatcher注册两个Uri
		matcher.addURI(Words.AUTHORITY, "words", WORDS);
		matcher.addURI(Words.AUTHORITY, "word", WORD);
	}

	// 第一次调用该DictProvider时系统先创建DictProvider对象并回调该方法
	@Override
	public boolean onCreate() {
		dbOpenHelper = new MyDatabaseHelper(getContext(), "myDict.db3", null, 1);
		return true;
	}

	// 返回指定Uri参数对应的数据的MIME类型
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (matcher.match(uri)) {
		case WORDS:
			return "vnd.android.cursor.dir/cn.treize.dict";

		case WORD:
			return "vnd.android.cursor.item/cn.treize.dict";
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (matcher.match(uri)) {
		case WORDS:
			return db.query("dict", projection, selection, selectionArgs, null,
					null, sortOrder);
		case WORD:
			// 解析出想查询的记录ID
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" + id;
			// 如果原来的where子句存在，拼接where子句
			if (selection != null && !"".equals(selection)) {
				whereClause = whereClause + " and " + selection;
			}
			return db.query("dict", projection, whereClause, selectionArgs,
					null, null, sortOrder);

		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

		long rowId = db.insert("dict", Words.Word._ID, values);
		if (rowId > 0) {
			// 在已有的Uri后面追加ID
			Uri wordUri = ContentUris.withAppendedId(uri, rowId);
			// 通知数据已经改变是·
			getContext().getContentResolver().notifyChange(wordUri, null);
			return wordUri;
		}

		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		// 记录所修改的记录数
		int num = 0;
		switch (matcher.match(uri)) {
		case WORDS:
			num = db.update("dict", values, selection, selectionArgs);
			break;
		case WORD:
			// 解析出想修改的记录ID
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" + id;
			if (selection != null && !selection.equals("")) {
				whereClause = whereClause + " and " + selection;
			}
			num = db.update("dict", values, whereClause, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
		// 通知数据已经改变
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		// 记录所删除的记录数
		int num = 0;
		switch (matcher.match(uri)) {
		case WORDS:
			num = db.delete("dict", selection, selectionArgs);
			break;
		case WORD:
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" + id;
			if (selection != null && !selection.equals("")) {
				whereClause = whereClause + " and " + selection;
			}
			num = db.delete("dict", whereClause, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}

}
