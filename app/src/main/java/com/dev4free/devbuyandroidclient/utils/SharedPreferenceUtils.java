package com.dev4free.devbuyandroidclient.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dev4free.devbuyandroidclient.application.MyApplication;

/**
 * Created by syd on 2016/4/26.
 */
public class SharedPreferenceUtils {

    public static SharedPreferences getDefaultSharedPreferences() {

       return PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
    }


}
