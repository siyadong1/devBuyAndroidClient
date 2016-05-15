package com.dev4free.devbuyandroidclient.application;

import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by syd on 2016/4/26.
 */
public class MyApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        PgyCrashManager.register(this);
    }
}
