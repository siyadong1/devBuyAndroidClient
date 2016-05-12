package com.dev4free.devbuyandroidclient.activity.main3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.activity.MainActivity;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by syd on 2016/5/12.
 */
public class PaymentSuccessActivity extends BaseActivity {


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payementsuccess);

        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initTitle("付款结果");

    }



    @Event(R.id.btn_paymentsuccess_confirm)
    private void clickEvent(View view) {

        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);

    }
}
