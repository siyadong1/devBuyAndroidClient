package com.dev4free.devbuyandroidclient.activity.main3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by syd on 2016/5/12.
 */
public class PaymentActivity extends BaseActivity {

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initTitle("确认付款");

    }




    @Event(R.id.btn_payment_confirm)
    private void clickEvent(View view) {

        Intent intent = new Intent(mContext,PaymentSuccessActivity.class);
        startActivity(intent);

    }
}
