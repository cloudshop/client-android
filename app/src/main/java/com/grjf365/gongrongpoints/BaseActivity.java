package com.grjf365.gongrongpoints;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.cordova.CordovaActivity;

public class BaseActivity extends AppCompatActivity {

    protected Activity activity;
    private ProgressDialog pdDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = this;
    }

    public void callJs(String message){}


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_to_right, R.anim.to_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_to_left, R.anim.to_left);
    }

    public ProgressDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    public ProgressDialog showWaitDialog(String message) {
        if(pdDialog == null){
            pdDialog = new ProgressDialog(this);
            pdDialog.setCanceledOnTouchOutside(true);
        }
        if(message != null){
            pdDialog.setMessage(message);
        }
        if(!pdDialog.isShowing()){
            pdDialog.show();
        }
        return pdDialog;
    }

    public void hideWaitDialog() {
        if(pdDialog != null && pdDialog.isShowing()){
            pdDialog.dismiss();
        }
    }
}
