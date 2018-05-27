package com.newdun.assist.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yingmingbo 模块配置
 */
public final class ModuleConfig {

	private static final ModuleConfig INSTANCE = new ModuleConfig();

	private ModuleConfig() {
		if (INSTANCE != null) {
			throw new IllegalStateException("Already instantiated");
		}

	}

	public static ModuleConfig getInstance() {
		if (values == null) {
			readConfigFromFile();
			setFieldEnable();
		}
		return INSTANCE;
	}

	/**
	 * 配置文件根目录
	 */
	private static String ASSERT_MODEL_CONFIG_PATH = "/assets/config/";
	private static Map<String, String> values = null;

	/**
	 * 读取渠道配置文件
	 */
	private static void readConfigFromFile() {
		values = new HashMap<String, String>();
		InputStream in = null;
		BufferedReader buff = null;
		String filePath = "";
		try {
			filePath = ASSERT_MODEL_CONFIG_PATH + "default.txt";
			in = ModuleConfig.class.getResourceAsStream(filePath);
			if (in == null) {
				filePath = ASSERT_MODEL_CONFIG_PATH + "default.txt";
				in = ModuleConfig.class.getResourceAsStream(filePath);
			}
			buff = new BufferedReader(new InputStreamReader(in));
			String content = buff.readLine();
			while (content != null) {
				String[] splitContent = content.split("=");
				if (splitContent != null) {
					values.put(splitContent[0].trim(), splitContent[1].trim());
				}
				content = buff.readLine();
			}
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			try {
				if (buff != null) {
					buff.close();
					buff = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
	}

	private static void setFieldEnable() {
		if (values.size() == 0) {
			return;
		}
		Field[] fields = ModuleConfig.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String value = values.get(field.getName());
			if (value == null || !("0".equals(value) || "1".equals(value)))
				continue;
			try {
				field.set(INSTANCE, "1".equals(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Fields debug : 是否支持调试
	 */
	private boolean debug = true;

	public boolean isDebuggable() {
		return debug;
	}
	
	public void setDebuggable(boolean debug) {
		this.debug = debug;
	}	

	private boolean show_launcher = true;

	public boolean enableLauncher() {
		return show_launcher;
	}
	
	public void setEnableLauncher(boolean enable) {
		this.show_launcher = enable;
	}
}
