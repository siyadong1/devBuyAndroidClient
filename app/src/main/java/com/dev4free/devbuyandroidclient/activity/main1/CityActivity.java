package com.dev4free.devbuyandroidclient.activity.main1;

import android.os.Bundle;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;

/**
 * Created by syd on 2016/4/26.
 */
public class CityActivity extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initTitle("所选城市");

    }


}
