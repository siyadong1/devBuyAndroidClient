package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by syd on 2016/5/9.
 */
public class AddressAddActivity extends BaseActivity {


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addressadd);
        initTitle("添加新地址");

        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

    }


    @Event(R.id.btn_addressadd_confirm)
    private void clickEvent(View view) {

        finish();
    }

}
