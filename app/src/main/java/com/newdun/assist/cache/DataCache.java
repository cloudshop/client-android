package com.newdun.assist.cache;

import android.content.Context;
import android.content.res.AssetManager;

import com.newdun.frame.app.BaseApp;
import com.newdun.frame.model.Content;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataCache {
	static public Content load(Class<?> clazz, String fileName) {
		Content object = null;
		BaseApp app = BaseApp.getApplication();
		String content;
		InputStream inputStream;

		try {
			inputStream = app.openFileInput(fileName);
		} catch (FileNotFoundException e) {
			AssetManager manager = app.getAssets();
			try {
				inputStream = manager.open(fileName + ".txt");
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			object = (Content) mapper.readValue(inputStream, clazz);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return object;
	}

	static public boolean save(Object object, String fileName) {
		try {
			BaseApp app = BaseApp.getApplication();
			FileOutputStream outStream = app.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(outStream, object);
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static List<Content> loadList(Class<?> clazz, String fileName) {
		BaseApp app = BaseApp.getApplication();
		String contentString;
		InputStream inputStream;

		try {
			inputStream = app.openFileInput(fileName);
		} catch (FileNotFoundException e) {
			AssetManager manager = app.getAssets();
			try {
				inputStream = manager.open(fileName + ".txt");
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeFactory typeFactory = mapper.getTypeFactory();
			CollectionType listType = typeFactory.constructCollectionType(
					List.class, clazz);
			List<Content> contents = null;
			contents = mapper.readValue(inputStream, listType);
			inputStream.close();
			return contents;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
