package com.dev4free.devbuyandroidclient.application;

import android.app.Application;
import android.content.Context;

import org.xutils.BuildConfig;
import org.xutils.x;


/**
 * Created by syd on 2016/4/26.
 */
public class BaseApplication extends Application {

   private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

    public static Context getContext(){

        return mContext;
    }

}
