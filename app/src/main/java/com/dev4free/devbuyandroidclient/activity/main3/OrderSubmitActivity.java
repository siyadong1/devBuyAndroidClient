package com.dev4free.devbuyandroidclient.activity.main3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.activity.main4.AddressManageActivity;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by syd on 2016/5/12.
 */
public class OrderSubmitActivity extends BaseActivity {

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;


    @ViewInject(R.id.rl_ordersubmit_goods)
    RelativeLayout rl_ordersubmit_goods;
    @ViewInject(R.id.rl_ordersubmit_address)
    RelativeLayout rl_ordersubmit_address;
    @ViewInject(R.id.btn_ordersubmit_submitorder)
    RelativeLayout btn_ordersubmit_submitorder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_order_submit);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initTitle("订单");

    }




    @Event(value = {R.id.rl_ordersubmit_goods,R.id.rl_ordersubmit_address,R.id.btn_ordersubmit_submitorder})
    private void clickEvent(View view) {
        Intent intent = null;
        switch (view.getId()) {



            //商品详情
            case R.id.rl_ordersubmit_goods:
                break;
            //地址
            case R.id.rl_ordersubmit_address:
                intent = new Intent(mContext, AddressManageActivity.class);
                startActivity(intent);
                break;


            //提交订单
            case R.id.btn_ordersubmit_submitorder:
                intent = new Intent(mContext,PaymentActivity.class);
                startActivity(intent);
                break;


        }

    }

}
