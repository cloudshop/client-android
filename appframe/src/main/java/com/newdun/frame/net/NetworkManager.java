package com.newdun.frame.net;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.newdun.frame.R;
import com.newdun.frame.config.ModuleConfig;
import com.newdun.frame.model.ResponseResult;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NetworkManager {
	private static final boolean DEBUG = true;
	private static final String LOG_TAG = NetworkManager.class.getSimpleName();

	// 网络相关
	private static final int MSG_SHOW_DIALOG = 100;
	private static final int MSG_HIDE_DIALOG = 101;
	private static final int MSG_NET_FINISHED = 102;
	private static final int MSG_NET_ERROR = 103;
	private static final int MSG_NET_PROGRESS = 104;

	private Context mContext;
	private Fragment mFragment;
	ITransactionListener mListener;
	protected boolean mShowDownloadingDialog = true;
	private Dialog mNetProgressDialog;
	private int mTasks;

	public NetworkManager(Context context) {
		mContext = context;
		mFragment = null;
		mListener = (ITransactionListener) context;
		mTasks = 0;
	}

	public NetworkManager(Fragment context) {
		mFragment = context;
		mContext = context.getActivity();
		mListener = (ITransactionListener) context;
		mTasks = 0;
	}

	public NetworkManager(Context context, ITransactionListener listener) {
		mContext = context;
		mListener = listener;
		mTasks = 0;
	}
	
	public void setContext(Context context, ITransactionListener listener) {
		mContext = context;
		mListener = listener;
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(final Message msg) {
			switch (msg.what) {
			case MSG_SHOW_DIALOG:
				mListener.onShowProgress(msg.arg1);
				break;
			case MSG_HIDE_DIALOG:
				mListener.onHideProgress(msg.arg1);
				break;
			case MSG_NET_FINISHED:
				mTasks--;
				int taskId = msg.arg1 & ITransactionListener.ID_MASK;
				int subId = msg.arg1 >> ITransactionListener.MASK_BITS;
				try {
					mListener.onNetFinished(taskId, subId, (ResponseResult) msg.obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (ModuleConfig.getInstance().isDebuggable()) {
					Log.d("HTTP",
							"HTTP TASK: "
									+ mTasks
									+ " THREAD: "
									+ (mThreads == null ? 0 : mThreads
											.getActiveCount()));
				}
				if ((mThreads == null || mThreads.getActiveCount() == 0)
						|| mTasks <= 0) {
					mListener.onHideProgress(-1);
					mListener.onNetFinished(-1, 0, null);
				}
				break;
			case MSG_NET_ERROR:
				mListener.onNetError(msg.arg1, (String) msg.obj);
				break;
			case MSG_NET_PROGRESS:
				mListener.onNetProgress(msg.arg1, msg.arg2);
				break;
			}
		}
	};

	BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);
	ThreadPoolExecutor mThreads = null;

	synchronized public void addTask(int taskId, NetRequireRunner runnable) {
		if (DEBUG&& ModuleConfig.getInstance().isDebuggable()) {
			Log.d(LOG_TAG, ">>>>>>>>>>>>>> addTask");
		}
		mTasks++;
		runnable.taskId = taskId;
		if (mThreads == null) {
			mThreads = new ThreadPoolExecutor(5, 20, 1, TimeUnit.DAYS,
					blockingQueue);
		}
		mThreads.execute(runnable);
	}

	synchronized public void onStop() {
		if (DEBUG&&ModuleConfig.getInstance().isDebuggable()) {
			Log.d(LOG_TAG, "cancelTask");
		}
		if (mThreads != null) {
			mThreads.shutdown();
			mThreads = null;
		}
	}

	public void onShowProgress(int taskId) {
		if (mShowDownloadingDialog
				&& (mNetProgressDialog == null || !mNetProgressDialog
						.isShowing())) {
			mNetProgressDialog = new Dialog(mContext,
					R.style.new_circle_progress);
			mNetProgressDialog.setContentView(R.layout.frame_progressbar);

			// mNetProgressDialog = new ProgressDialog(mContext);
			// mNetProgressDialog.setContentView(R.layout.vg_progressbar);
			// mNetProgressDialog
			// .setOnDismissListener(new DialogInterface.OnDismissListener() {
			// public void onDismiss(DialogInterface dialog) {
			// onStop();
			// }
			// });
			mNetProgressDialog.show();
		}
	}

	public void onHideProgress(int taskId) {
		if (DEBUG&&ModuleConfig.getInstance().isDebuggable()) {
			Log.d(LOG_TAG, ">>>>>>>>>>>>>> onHideProgress");
		}
		try {
		if (mNetProgressDialog != null) {
			if (mNetProgressDialog.isShowing()) {
				mNetProgressDialog.dismiss();
			}
			mNetProgressDialog = null;
		}
		}
		catch (Exception e) {
			
		}
	}

	public class NetRequireRunner implements Runnable {
		int taskId = -1;

		public void run() {
			ResponseResult result = new ResponseResult(ResponseResult.ERROR_OK,
					"");
			Message msg = new Message();
			msg.what = MSG_SHOW_DIALOG;
			msg.arg1 = taskId;
			mHandler.sendMessage(msg);

			Object ret = onNetRequire(result);
			if (ret == null) {

			} else if (ret instanceof ResponseResult) {
				if (ret != result) {
					ResponseResult retResp = (ResponseResult) ret;
					result.code = retResp.code;
					result.msg = retResp.msg;
					result.mAppendInfo = retResp.mAppendInfo;
					result.mContent = retResp.mContent;
				}
			} else {
				result.mContent = ret;
			}

			msg = new Message();
			msg.what = MSG_HIDE_DIALOG;
			msg.arg1 = taskId;
			mHandler.sendMessage(msg);

			msg = new Message();
			msg.what = MSG_NET_FINISHED;
			msg.arg1 = taskId;
			msg.obj = result;
			mHandler.sendMessage(msg);
		}

		public Object onNetRequire(ResponseResult result) {
			return result;
		}
	}

	public Dialog getProcessBar() {
		return mNetProgressDialog;
	}
}
