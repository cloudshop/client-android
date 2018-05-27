package com.newdun.frame.model;


public class ResponseResult {

	public static final int ERROR_OK = 0;
	public static final int ERROR_UNKNOWN = -10000;
	public static final int ERROR_NETWORK = -10001;
	public static final int ERROR_SERVICE = -10002;
	public static final String STR_ERROR_UNKNOWN = "出现错误，我们正在分析^^！";
	public static final String STR_ERROR_NETWORK = "网络不给力，站在楼顶对着太阳试试！";
	public static final String STR_ERROR_SERVICE = "当前的网络服务器不给力，汇学继续加油！";

	public final static ResponseResult ResultErrOk = new ResponseResult(
			ERROR_OK, "");
	public final static ResponseResult ResultErrUnknown = new ResponseResult(
			ERROR_UNKNOWN, STR_ERROR_UNKNOWN);
	public final static ResponseResult ResultErrNetwork = new ResponseResult(
			ERROR_NETWORK, STR_ERROR_NETWORK);
	public final static ResponseResult ResultErrService = new ResponseResult(
			ERROR_SERVICE, STR_ERROR_SERVICE);

	public int code = ERROR_UNKNOWN;
	public String msg = STR_ERROR_UNKNOWN;
	public Object mContent = null;
	public Object mAppendInfo = null;

	public ResponseResult() {
	}

	public ResponseResult(int status, String desc) {
		code = status;
		this.msg = desc;
	}

}