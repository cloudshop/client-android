package com.grjf365.gongrongpoints.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * @author victor
 */
@SuppressWarnings("deprecation")
public final class FileUtil {

	/**
	 * 获取SD卡路径
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getSDPath(Context context) throws Exception {
		// String sdDir = null;
		// boolean sdCardExist = Environment.getExternalStorageState().equals(
		// Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		// if (sdCardExist) {
		// sdDir =
		// Environment.getExternalStorageDirectory().getAbsolutePath();// 获取根目录
		// } else {
		// throw new Exception("没有SD卡");
		// }
		// return sdDir;
		String sdDir = null;
		File path = context.getExternalFilesDir(null); // 判断sd卡是否存在
		if (path != null) {
			sdDir = path.getAbsolutePath();// 获取根目录
		} else {
			throw new Exception("访问文件数据目录失败");
		}
		return sdDir;
	}

	public static String getProcessName(Context cxt, int pid) {
		ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
		if (runningApps == null) {
			return null;
		}
		for (RunningAppProcessInfo procInfo : runningApps) {
			if (procInfo.pid == pid) {
				return procInfo.processName;
			}
		}
		return null;
	}

	public static String formatDate(Long time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date curDate = new Date(time);// 获取当前时间
		String str = formatter.format(curDate);
		// 时间格式化获取不到后两位的毫秒
		String curm = time + "";
		String lastTwo = curm.substring(curm.length() - 2, curm.length());
		// LogUtil.info(" time = "+time+" lastTwo = "+lastTwo);
		return (str + ":" + lastTwo);
	}

	/**
	 * 生成行为log的ID
	 * 
	 * @param time
	 * @return
	 */
	public static String formatDateRandom(Long time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

		Date curDate = new Date(time);// 获取当前时间
		String str = formatter.format(curDate);
		// 时间格式化获取不到后两位的毫秒
		String curm = time + "";
		String lastTwo = curm.substring(curm.length() - 2, curm.length());
		Random random = new Random();
		int randomNum = random.nextInt(89999) + 10000;
		// LogUtil.info(" randomId = "+(str+randomNum));
		return (str + lastTwo + randomNum);
	}

	/**
	 * 递归删除文件夹和文件夹内的所有文件
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean delete(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// 递归删除目录中的子目录
			for (int i = 0; i < children.length; i++) {
				boolean success = delete(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		File to = new File(dir.getAbsolutePath() + System.currentTimeMillis());
		dir.renameTo(to);
		return to.delete();
	}

	/**
	 * 将文件读取为字符串
	 * 
	 * @param fullFilename
	 * @return
	 */
	public static String readFileContentStr(String fullFilename) {
		String readOutStr = null;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(fullFilename));
			try {
				long len = new File(fullFilename).length();
				if (len > Integer.MAX_VALUE)
					throw new IOException("File " + fullFilename + " too large, was " + len + " bytes.");
				byte[] bytes = new byte[(int) len];
				dis.readFully(bytes);
				readOutStr = new String(bytes, "UTF-8");
			} finally {
				dis.close();
			}
		} catch (IOException e) {
			readOutStr = null;
		}
		return readOutStr;
	}

	/**
	 * 拷贝文件
	 * 
	 * @throws IOException
	 */
	public static void copyFile(String srcPath, String targetPath) throws IOException {
		File srcFile = new File(srcPath);
		File targetFile = new File(targetPath);
		FileInputStream is = new FileInputStream(srcFile);
		FileOutputStream fos = new FileOutputStream(targetFile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		is.close();
	}

	/**
	 * 拷贝assets目录下的文件
	 * 
	 * @throws IOException
	 */
	public static void copyAssets(Context context, String filename, String targetPath) throws IOException {
		File targetFile = new File(targetPath);
		AssetManager am = context.getAssets();
		InputStream is = am.open(filename);
		FileOutputStream fos = new FileOutputStream(targetFile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		is.close();
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 * @throws IOException
	 */
	public void copyFolder(String oldPath, String newPath) throws IOException {
		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
		File a = new File(oldPath);
		String[] file = a.list();
		File temp = null;
		for (int i = 0; i < file.length; i++) {
			if (oldPath.endsWith(File.separator)) {
				temp = new File(oldPath + file[i]);
			} else {
				temp = new File(oldPath + File.separator + file[i]);
			}
			if (temp.isFile()) {
				FileInputStream input = new FileInputStream(temp);
				FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			}
			if (temp.isDirectory()) {// 如果是子文件夹
				copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
			}
		}
	}

	/**
	 * 获取手机内部可用空间大小
	 * 
	 * @return
	 */
	static public long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		String size = getDataSize(availableBlocks * blockSize);
		return availableBlocks * blockSize;
	}

	static public long getAvailableInternalMemorySize1() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File sdcardDir = Environment.getExternalStorageDirectory();
			StatFs sf = new StatFs(sdcardDir.getPath());
			long blockSize = sf.getBlockSize();
			long blockCount = sf.getBlockCount();
			long availCount = sf.getAvailableBlocks();
			String size = getDataSize(availCount * blockSize);
			Log.d("", "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + blockSize * blockCount / 1024 + "KB");
			Log.d("", "可用的block数目：:" + availCount + ",剩余空间:" + availCount * blockSize / 1024 + "KB");
			return availCount * blockSize;
		}
		return -1;
	}

	/**
	 * 获取手机内部空间大小
	 * 
	 * @return
	 */
	static public long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();// Gets the Android data
													// directory
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize(); // 每个block 占字节数
		long totalBlocks = stat.getBlockCount(); // block总数
		return totalBlocks * blockSize;
	}

	/**
	 * 获取手机外部可用空间大小
	 * 
	 * @return
	 */
	static public long getAvailableExternalStorageSize() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File path = Environment.getExternalStorageDirectory();// 获取SDCard根目录
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		} else {
			return -1;
		}
	}

	/**
	 * 获取手机外部总空间大小
	 * 
	 * @return
	 */
	static public long getTotalExternalMemorySize() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File path = Environment.getExternalStorageDirectory(); // 获取SDCard根目录
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		} else {
			return -1;
		}
	}

	/**
	 * 返回byte的数据大小对应的文本
	 * 
	 * @param size
	 * @return
	 */
	public static String getDataSize(long size) {
		DecimalFormat formater = new DecimalFormat("####.00");
		if (size < 1024f) {
			return size + "B";
		} else if (size < 1024f * 1024f) {
			float kbsize = size / 1024f;
			return formater.format(kbsize) + "KB";
		} else if (size < 1024f * 1024f * 1024f) {
			float mbsize = size / 1024f / 1024f;
			return formater.format(mbsize) + "MB";
		} else if (size < 1024f * 1024f * 1024f * 1024f) {
			float gbsize = size / 1024f / 1024f / 1024f;
			return formater.format(gbsize) + "GB";
		} else {
			return "size: error";
		}
	}

	/**
	 * 
	 * 调用此方法自动计算指定文件或指定文件夹的大小
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 计算好的带B、KB、MB、GB的字符串
	 */
	public static String getAutoFileOrFilesSize(String filePath) {
		File file = new File(filePath);
		long blockSize = 0;
		try {
			if (file.isDirectory()) {
				blockSize = getFileSizes(file);
			} else {
				blockSize = getFileSize(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatFileSize(blockSize);
	}

	/**
	 * 获取指定文件大小
	 * 
	 * @return
	 * @throws Exception
	 */
	public static long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
			fis.close();
		} else {
			file.createNewFile();
		}
		return size;
	}

	/**
	 * 获取指定文件夹
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private static long getFileSizes(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSizes(flist[i]);
			} else {
				size = size + getFileSize(flist[i]);
			}
		}
		return size;
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return
	 */
	public static String formatFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#");
		String fileSizeString = "";
		String wrongSize = "0B";
		if (fileS <= 0) {
			return wrongSize;
		}
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "GB";
		}
		return fileSizeString;
	}

	public static boolean deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 获取文件大小
	 * 
	 * @return
	 */
	public static long getFileLength(File file) {
		long size = 0;
		if (file != null && file.exists()) {
			size = file.length();
		}
		return size;
	}

	public static boolean isExist(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * 将字符串写成文件
	 * 
	 * @param content
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean writeTxtFile(String content, File fileName) throws Exception {
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("UTF-8"));
			o.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mm != null) {
				mm.close();
			}
		}
		return flag;
	}
}
