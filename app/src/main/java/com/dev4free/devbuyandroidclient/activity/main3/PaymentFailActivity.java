package com.dev4free.devbuyandroidclient.activity.main3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.activity.MainActivity;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by syd on 2016/8/29.
 */
public class PaymentFailActivity extends BaseActivity {


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    @ViewInject(R.id.tv_paymentfail_reason)
    TextView tv_paymentfail_reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payementfail);

        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initTitle("付款结果");

        tv_paymentfail_reason.setText("失败原因：" + getIntent().getStringExtra("paymentfail_reason"));
    }



    @Event(R.id.btn_paymentsuccess_confirm)
    private void clickEvent(View view) {

        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);

    }
}
