package com.grjf365.gongrongpoints.wxapi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.grjf365.gongrongpoints.R;
import com.grjf365.gongrongpoints.config.Constant;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, Constant.PAY_WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}


	/**
	 * 0 成功 展示成功页面 -1 错误 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
	 * -2 用户取消 无需处理。发生场景：用户不支付了，点击取消，返回APP。
	 */
	@Override
	public void onResp(BaseResp resp) {
		Intent intent = new Intent();
		intent.setAction("com.grjf365.gongrongpoints.WXPAY");
		intent.putExtra("payResult",resp.errCode+"");
		sendBroadcast(intent);
		switch (resp.errCode) {

			case 0:
				Toast.makeText(this,"微信支付成功",Toast.LENGTH_SHORT).show();

				break;
			case -1:
				Toast.makeText(this,"微信支付错误",Toast.LENGTH_SHORT).show();
				break;

			case -2:
				Toast.makeText(this,"微信支付取消",Toast.LENGTH_SHORT).show();
				break;

			default:
				Toast.makeText(this,"未知错误",Toast.LENGTH_SHORT).show();
				break;

		}
		// 完成回调后结束当前页面
		finish();
	}
}