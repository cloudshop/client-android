package com.newdun.assist.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.newdun.frame.app.BaseApp;
import com.newdun.frame.model.Content;
import com.newdun.frame.provider.DatabaseHelper;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)

public class City extends Content implements Parcelable {
	
	private static final String TAG = City.class.getSimpleName();
	private static final boolean DEBUG = true;
	
	public String areaId = "110100"; // 城市ID
	public String areaName = "北京"; //城市名称
	
	public int baiduId = 131;
	
	public String longitude = "116.395645";
	public String latitude = "39.929986";
	public String scale = "12";

	public City() {
	}

	public City(String id, String name){
		
		this.areaId = id;
		this.areaName = name;
	}
	
	
	public City(Cursor cursor) {
		super(cursor);

		if (cursor != null) {

		}
	}
	
	public void putContent(ContentValues initialValues) {
		if (DEBUG)
			Log.v(TAG, "putContent");

		super.putContent(initialValues);
	}

	
	public interface Columns extends Content.Columns {
		
		public static final String[] FULL_PROJECTION = {
				_ID, // 0
				REMOTE_ID, // 1
				CONTENT, // 2
		};

	}

	public static final class Table extends Content.Table implements Columns {
		public static final String TABLE_NAME = City.class.getSimpleName();

		public static final Uri CONTENT_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + TABLE_NAME);
	}

	@Override
	public void createTable(SQLiteDatabase db) {
		DatabaseHelper.createTable(db, Table.TABLE_NAME,
				""
    		);
	}
	
	@Override
	public void clearCache(String filter) {
        BaseApp.getApplication().getContentResolver()
        	.delete(Table.CONTENT_URI, null, null);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(toString());
	}
	
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};
	
	City(Parcel in) {
		String content = in.readString();
//		assign(content);
	}

    public static final Parcelable.Creator<City> CREATOR
            = new Parcelable.Creator<City>() {
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        public City[] newArray(int size) {
            return new City[size];
        }
    };
}