package com.dev4free.devbuyandroidclient.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 *
 * Created by syd on 2015/12/25.
 *
 */
public class ProgressDialogUtils {


Context mContext;
ProgressDialog mProgressDialog;

    public ProgressDialogUtils(Context mContext) {

        this.mContext = mContext;
        mProgressDialog = new ProgressDialog(mContext);

    }


    public  void showProgress(String  message) {

            mProgressDialog.setMessage(message);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
    }

    /**
     * 提示：正在连接服务器
     */
    public  void showProgress() {

            mProgressDialog.setMessage("正在连接服务器...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

    }


    /**
     * 关闭Progress提示框
     */
    public  void dismissProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();


        }

    }


    /**
     * mProgressDialog是否还在显示
     * @return
     */

    public boolean isShowing() {
        return  mProgressDialog.isShowing();
    }

}
