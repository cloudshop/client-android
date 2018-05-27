package com.newdun.frame.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.newdun.frame.model.Content;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "DatabaseHelper";

	protected static DatabaseHelper sInstance = null;
	private static boolean sTriedAutoIncrement = false;
	private static boolean sFakeLowStorageTest = false; // for testing only

	static final String DATABASE_NAME = "content.db";
	static final int DATABASE_VERSION = 1;
	private final Context mContext;
	private LowStorageMonitor mLowStorageMonitor;

	protected DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}

	protected DatabaseHelper(Context context, String name,
                             CursorFactory factory, int version) {
		super(context, name == null ? DATABASE_NAME : name, factory, version);
		mContext = context;
	}

	/**
	 * Return a singleton helper for database.
	 */
	/* package */public static synchronized DatabaseHelper getInstance(Context context) {
		if (sInstance == null) {
			sInstance = new DatabaseHelper(context);
		}
		return sInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	private void createCommonTriggers(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ currentVersion + ".");

		switch (oldVersion) {
		// fall through
		case 1:
			if (currentVersion <= 1) {
				return;
			}

			db.beginTransaction();
			try {
				upgradeDatabaseToVersion1(db);
				db.setTransactionSuccessful();
			} catch (Throwable ex) {
				Log.e(TAG, ex.getMessage(), ex);
				break;
			} finally {
				db.endTransaction();
			}
			return;
		}

		Log.e(TAG, "Destroying all old data.");
		dropAll(db);
		onCreate(db);
	}

	private void dropAll(SQLiteDatabase db) {
		// Clean the database out in order to start over from scratch.
		// We don't need to drop our triggers here because SQLite automatically
		// drops a trigger when its attached database is dropped.
		// db.execSQL("DROP TABLE IF EXISTS canonical_addresses");
	}

	private void upgradeDatabaseToVersion1(SQLiteDatabase db) {

	}

	public static void createTable(SQLiteDatabase db, String table, String append) {
		String sql = "CREATE TABLE IF NOT EXISTS " + table + " ("
				+ Content.Table._ID + " INTEGER PRIMARY KEY,"
				+ Content.Table.REMOTE_ID + " INTEGER,"
				+ Content.Table.CONTENT + " TEXT,"
				+ append 
				+ " UNIQUE (" + Content.Table.REMOTE_ID
				+ ")" + ");";
		db.execSQL(sql);
	}

	public static void dropTable(SQLiteDatabase db, String table) {
		String sql = "DROP TABLE IF EXISTS " + table;
		db.execSQL(sql);
	}

//	@Override
//	public synchronized SQLiteDatabase getWritableDatabase() {
//		SQLiteDatabase db = super.getWritableDatabase();
//
//		if (!sTriedAutoIncrement) {
//			sTriedAutoIncrement = true;
//			boolean hasAutoIncrementThreads = hasAutoIncrement(db, "threads");
//			boolean hasAutoIncrementContactes = hasAutoIncrement(db,
//					"canonical_addresses");
//			Log.d(TAG, "[getWritableDatabase] hasAutoIncrementThreads: "
//					+ hasAutoIncrementThreads + " hasAutoIncrementContactes: "
//					+ hasAutoIncrementContactes);
//			boolean autoIncrementThreadsSuccess = true;
//			boolean autoIncrementContactesSuccess = true;
//			if (!hasAutoIncrementThreads) {
//				db.beginTransaction();
//				try {
//					db.setTransactionSuccessful();
//				} catch (Throwable ex) {
//					Log.e(TAG,
//							"Failed to add autoIncrement to threads;: "
//									+ ex.getMessage(), ex);
//					autoIncrementThreadsSuccess = false;
//				} finally {
//					db.endTransaction();
//				}
//			}
//			if (!hasAutoIncrementContactes) {
//				db.beginTransaction();
//				try {
//					db.setTransactionSuccessful();
//				} catch (Throwable ex) {
//					Log.e(TAG,
//							"Failed to add autoIncrement to canonical_addresses: "
//									+ ex.getMessage(), ex);
//					autoIncrementContactesSuccess = false;
//				} finally {
//					db.endTransaction();
//				}
//			}
//			if (autoIncrementThreadsSuccess && autoIncrementContactesSuccess) {
//				if (mLowStorageMonitor != null) {
//					// We've already updated the database. This receiver is no
//					// longer necessary.
//					Log.d(TAG,
//							"Unregistering mLowStorageMonitor - we've upgraded");
//					mContext.unregisterReceiver(mLowStorageMonitor);
//					mLowStorageMonitor = null;
//				}
//			} else {
//				if (sFakeLowStorageTest) {
//					sFakeLowStorageTest = false;
//				}
//
//				// We failed, perhaps because of low storage. Turn on a receiver
//				// to watch for
//				// storage space.
//				if (mLowStorageMonitor == null) {
//					Log.d(TAG,
//							"[getWritableDatabase] turning on storage monitor");
//					mLowStorageMonitor = new LowStorageMonitor();
//					IntentFilter intentFilter = new IntentFilter();
//					intentFilter.addAction(Intent.ACTION_DEVICE_STORAGE_LOW);
//					intentFilter.addAction(Intent.ACTION_DEVICE_STORAGE_OK);
//					mContext.registerReceiver(mLowStorageMonitor, intentFilter);
//				}
//			}
//		}
//		return db;
//	}

	// Determine whether a particular table has AUTOINCREMENT in its schema.
	private boolean hasAutoIncrement(SQLiteDatabase db, String tableName) {
		boolean result = false;
		String query = "SELECT sql FROM sqlite_master WHERE type='table' AND name='"
				+ tableName + "'";
		Cursor c = db.rawQuery(query, null);
		if (c != null) {
			try {
				if (c.moveToFirst()) {
					String schema = c.getString(0);
					result = schema != null ? schema.contains("AUTOINCREMENT")
							: false;
					Log.d(TAG, "tableName: " + tableName
							+ " hasAutoIncrement: " + schema + " result: "
							+ result);
				}
			} finally {
				c.close();
			}
		}
		return result;
	}

	private class LowStorageMonitor extends BroadcastReceiver {

		public LowStorageMonitor() {
		}

		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			Log.d(TAG, "[LowStorageMonitor] onReceive intent " + action);

			if (Intent.ACTION_DEVICE_STORAGE_OK.equals(action)) {
				sTriedAutoIncrement = false; // try to upgrade on the next
												// getWriteableDatabase
			}
		}
	}
}
