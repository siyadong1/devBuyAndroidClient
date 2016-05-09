package com.dev4free.devbuyandroidclient.activity.main4;

import android.os.Bundle;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;

/**
 * Created by syd on 2016/5/9.
 */
public class AboutUsActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        initTitle("关于我们");
    }
}
