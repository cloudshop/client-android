package com.newdun.frame.net;

import android.graphics.Bitmap;

import com.newdun.frame.parser.IDataParser;

import java.io.InputStream;
import java.util.Map;


public interface ITransaction {
	String getUrl();

	Object getContent(String strHashCode, long vid, Map params, boolean addCommon, IDataParser parser) throws Exception;
	Object commit(String strHashCode, Map params, boolean addCommon, IDataParser parser) throws Exception;
	Object upload(String type, String id, InputStream data, Map params, boolean addCommon, IDataParser parser) throws Exception;
	Object uploadImage(String type, String id, Bitmap data, Map params, boolean addCommon, IDataParser parser) throws Exception;
	
	Object listContents(String strHashCode, long intCurrentPos, long intPageRowSize,
						Map params, boolean addCommon, IDataParser parser) throws Exception;

	Object login(String userName, String password, boolean auto, double latitude, double longitude) throws Exception;

	Object logout();


}