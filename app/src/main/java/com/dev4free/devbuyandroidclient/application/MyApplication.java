package com.dev4free.devbuyandroidclient.application;

import android.app.Application;
import android.content.Context;

import com.dev4free.devbuyandroidclient.exception.LocalFileHandler;
import com.pgyersdk.crash.PgyCrashManager;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by syd on 2016/4/26.
 */
public class MyApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        PgyCrashManager.register(this);
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(mContext));
    }


    public static Context getContext(){

        return mContext;
    }

}
