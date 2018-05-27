package com.newdun.frame.net;

import com.newdun.frame.model.ResponseResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface ITransactionListener {
	public static int ID_MASK = 0xFFFF;
	public static int MASK_BITS = 16;
	
	// 显示连网进度
	public void onShowProgress(int taskId);

	// 关闭连网进度
	public void onHideProgress(int taskId);

//	// 判断本地网络状态
//	public boolean checkNetworkState();

	public void onNetError(int taskId, String exception);

	public void onNetProgress(int taskId, int percent);

	public void onNetFinished(int taskId, int subId, ResponseResult result);

	public Object onFetchData(int taskId, int subId, Object ...params) throws TimeoutException, ExecutionException, InterruptedException;
}
