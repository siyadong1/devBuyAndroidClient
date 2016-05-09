package com.dev4free.devbuyandroidclient.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.dev4free.devbuyandroidclient.application.MyApplication;

/**
 * Created by syd on 2015/12/23.
 */
public class MeasureUtils {

    public static DisplayMetrics  getDisplayMetrics () {

        WindowManager manager = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }


}
