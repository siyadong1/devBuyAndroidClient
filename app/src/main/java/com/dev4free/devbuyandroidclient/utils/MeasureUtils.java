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



    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
