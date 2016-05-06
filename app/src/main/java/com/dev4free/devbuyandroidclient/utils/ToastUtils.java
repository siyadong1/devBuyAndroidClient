package com.dev4free.devbuyandroidclient.utils;

import android.widget.Toast;

import com.dev4free.devbuyandroidclient.application.MyApplication;

/**
 * Created by syd on 2015/12/16.
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getContext(),message,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }



}
