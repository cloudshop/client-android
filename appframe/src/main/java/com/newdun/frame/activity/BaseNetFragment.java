package com.newdun.frame.activity;

import android.content.Context;
import android.util.Log;
import android.widget.PopupWindow;

import com.newdun.frame.config.ModuleConfig;
import com.newdun.frame.model.ResponseResult;
import com.newdun.frame.net.ITransactionListener;
import com.newdun.frame.net.NetworkManager;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


/**
 * Created by ymb on 16/11/3.
 */

public class BaseNetFragment extends BaseFragment implements ITransactionListener {

    public NetworkManager mNetworkManager;

    private PopupWindow mPopupWindow;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mNetworkManager = new NetworkManager(this);
    }

    @Override
    public void onDestroy() {
        mNetworkManager.onStop();
        super.onDestroy();
    }

    /**
     * 从网络异步返回数据
     * @param taskId
     * @param subId
     * @return 数据
     */
    public void fetchData(final int taskId, final int subId, final Object ...params) {
        addTask(taskId, subId, mNetworkManager.new NetRequireRunner() {
            @Override
            public Object onNetRequire(ResponseResult result) {
                try {
                    return onFetchData(taskId, subId, params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            });
    }

    /**
     * 从网络返回数据，运行在后台线程，不能直接访问UI的变量
     * @param taskId
     * @param subId
     * @return 数据
     */
    public Object onFetchData(int taskId, int subId, Object ...params) throws TimeoutException, ExecutionException, InterruptedException {
        return null;
    }

     void addTask(int taskId, NetworkManager.NetRequireRunner netRequireRunner) {
        mNetworkManager.addTask(taskId, netRequireRunner);
    }

    private void addTask(int taskId, int subId, NetworkManager.NetRequireRunner netRequireRunner) {
        taskId = (subId << MASK_BITS) | (taskId & ID_MASK);
        mNetworkManager.addTask(taskId, netRequireRunner);
    }

    @Override
    public void onShowProgress(int taskId) {
        try {
            mNetworkManager.onShowProgress(taskId);
        } catch (Exception e) {

        }
    }

    @Override
    public void onHideProgress(int taskId) {
        mNetworkManager.onHideProgress(taskId);
    }

    @Override
    public void onNetError(int taskId, String exception) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNetProgress(int taskId, int percent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onNetFinished(int taskId, int subId, ResponseResult result) {
        if (ModuleConfig.getInstance().isDebuggable()) {
            Log.d("NET", "Activity onNetFinished: " + taskId + " status: "
                    + ((result != null) ? String.valueOf(result.code) : "null"));
        }
    }
}
