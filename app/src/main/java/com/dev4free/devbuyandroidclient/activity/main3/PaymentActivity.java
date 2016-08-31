package com.dev4free.devbuyandroidclient.activity.main3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syd on 2016/5/12.
 */
public class PaymentActivity extends BaseActivity {

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;


    @ViewInject(R.id.tv_payment_username)
    TextView tv_payment_username;
    @ViewInject(R.id.tv_payment_balance)
    TextView tv_payment_balance;
    @ViewInject(R.id.tv_payment_price)
    TextView tv_payment_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);
        initTitle("确认付款");
        getUserBalance();
        tv_payment_username.setText(SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,""));
        tv_payment_price.setText(getIntent().getStringExtra("price"));
    }




    @Event(R.id.btn_payment_confirm)
    private void clickEvent(View view) {

        confirmPayment();

    }


    /**
     * 确认付款
     */
    private void confirmPayment() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("orders_id",getIntent().getStringExtra("orders_id"));

        HttpUtils.post(ConstantsUrl.payForOrdersByOrdersId, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        Intent intent = new Intent(mContext,PaymentSuccessActivity.class);
                        startActivity(intent);


                    } else {
                        Intent intent = new Intent(mContext,PaymentFailActivity.class);
                        intent.putExtra("paymentfail_reason",result.optString("content"));
                        startActivity(intent);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialogUtils.showAlertDialog(mContext,getString(R.string.json_parse_error));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                progressDialogUtils.dismissProgress();
                AlertDialogUtils.showAlertDialog(mContext,getString(R.string.server_error));
            }
        });




    }


    /**
     * 获取用户余额
     */
    public void getUserBalance() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);

        HttpUtils.post(ConstantsUrl.userWalletQuery, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        tv_payment_balance.setText(result.optString("content"));

                    } else {
                        AlertDialogUtils.showAlertDialog(mContext,result.getString(ConstantsHttp.CONTENT));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialogUtils.showAlertDialog(mContext,getString(R.string.json_parse_error));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                progressDialogUtils.dismissProgress();
                AlertDialogUtils.showAlertDialog(mContext,getString(R.string.server_error));
            }
        });





    }
}
