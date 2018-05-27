package com.newdun.frame.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.newdun.frame.provider.RoadProvider;

import org.codehaus.jackson.JsonNode;

public class Content {
	
	private static final String TAG = "BaseContent";
	private static final boolean DEBUG = true;

	// 无效ID
	final static public long INVALID_ID = -1;

	public Content(Cursor cursor) {
		if (cursor != null) {
//			mId = cursor.getLong(Columns.COLUMN_ID);
//			mRemoteId = cursor.getLong(Columns.COLUMN_REMOTE_ID);
		}
	}

	public Content() {
	}
	
	public void putContent(ContentValues initialValues) {
		if (DEBUG)
			Log.v(TAG, "putContent");

//		initialValues.put(Columns.REMOTE_ID, mRemoteId);
	}

	public interface Columns {
		public static final String _ID = "_id";
		public static final String REMOTE_ID = "remote_id";
		public static final String CONTENT = "content";

		public static final int COLUMN_ID = 0;
		public static final int COLUMN_REMOTE_ID = 1;
		public static final int COLUMN_CONTENT = 2;
		public static final int COLUMN_FIXED = 2;
	}

	public static class Table implements Columns {
		protected static final String AUTHORITY = RoadProvider.AUTHORITY;
		public static final String TABLE_NAME = "base";

		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + TABLE_NAME);
		public static final String DEFAULT_SORT_ORDER = "date DESC";
	}

	public void createTable(SQLiteDatabase db) {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clearCache(String filter) {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void assignLight(JsonNode node) throws Exception {
		throw new Exception();
	}

	public void assign(JsonNode node) throws Exception {
		throw new Exception();
	}
	
}
