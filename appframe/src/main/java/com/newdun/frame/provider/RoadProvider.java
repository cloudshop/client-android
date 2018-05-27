package com.newdun.frame.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.util.List;

/**
 * This class provides the ability to query the MMS and SMS databases at the
 * same time, mixing messages from both in a single thread (A.K.A.
 * conversation).
 * 
 * A virtual column, MmsSms.TYPE_DISCRIMINATOR_COLUMN, may be requested in the
 * projection for a query. Its value is either "mms" or "sms", depending on
 * whether the message represented by the row is an MMS message or an SMS
 * message, respectively.
 * 
 * This class also provides the ability to find out what addresses participated
 * in a particular thread. It doesn't support updates for either of these.
 * 
 * This class provides a way to allocate and retrieve thread IDs. This is done
 * atomically through a query. There is no insert URI for this.
 * 
 * Finally, this class provides a way to delete or update all messages in a
 * thread.
 */
public class RoadProvider extends ContentProvider {
	private static final String TAG = "RoadProvider";
	private static final boolean DEBUG = true;

	private static final String VND_ANDROID_DIR_MMS_SMS = "vnd.android-dir/mms-sms";

	public static String AUTHORITY;

	static {
		Log.d(TAG, "BaseContentProvider STATIC");

		AUTHORITY = RoadProvider.class.getPackage().getName();;
//		AUTHORITY = RoadApp.getApplication().getPackageName();
	}

	protected SQLiteOpenHelper mOpenHelper;

	@Override
	public boolean onCreate() {
		mOpenHelper = DatabaseHelper.getInstance(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		Cursor cursor = null;

		String table = getTableName(uri);
		long id = getUriID(uri);
		if (id != -1) {
			cursor = db.query(table, projection, "_ID=" + id, null, null, null,
					sortOrder);
		} else {
			cursor = db.query(table, projection, selection, selectionArgs,
					null, null, sortOrder);
		}

		if (cursor != null) {
			getContext().getContentResolver().notifyChange(
					getUriExceptID(table), null);
		}

		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return VND_ANDROID_DIR_MMS_SMS;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int affectedRows = 0;

		String table = getTableName(uri);
		long id = getUriID(uri);
		if (id != -1) {
			affectedRows = db.delete(table, "_ID=" + id, null);
		} else {
			affectedRows = db.delete(table, selection, selectionArgs);
		}

		if (affectedRows > 0) {
			getContext().getContentResolver().notifyChange(
					getUriExceptID(table), null);
		}

		return affectedRows;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		Context context = getContext();
		long rowID = 0;
		Uri returnUri;
		if (DEBUG) {
			Log.d(TAG, "insert->uri:" + uri);
		}

		String table = getTableName(uri);
		rowID = db.insert(table, null, values);

		if (rowID > 0) {
			returnUri = Uri.parse("content://" + AUTHORITY + "/" + table + "/"
					+ rowID);
			context.getContentResolver().notifyChange(returnUri, null);
			return returnUri;
		} else {
			Log.e(TAG, "insert: failed! " + values.toString());
		}

		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int affectedRows = 0;

		String table = getTableName(uri);
		long id = getUriID(uri);
		if (id != -1) {
			affectedRows = db.update(table, values, "_ID=" + id, null);
		} else {
			affectedRows = db.update(table, values, selection, selectionArgs);
		}

		if (affectedRows > 0) {
			getContext().getContentResolver().notifyChange(
					getUriExceptID(table), null);
		}

		return affectedRows;
	}

	private String getTableName(Uri uri) {
		List<String> tables = uri.getPathSegments();
		return tables.get(0);
	}

	private long getUriID(Uri uri) {
		long id = -1;
		try {
			id = ContentUris.parseId(uri);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return id;
	}

	private Uri getUriExceptID(String table) {
		Uri newUri = Uri.parse("content://" + AUTHORITY + "/" + table);
		return newUri;
	}
}
