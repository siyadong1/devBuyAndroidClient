package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

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
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syd on 2016/8/31.
 */
public class WalletRechargeActivity extends BaseActivity {

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    @ViewInject(R.id.et_walletrecharge_money)
    EditText et_walletrecharge_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_walletrecharge);
        x.view().inject(this);
        initTitle("DevBuy钱包充值");
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

    }



    @Event(value = {(R.id.btn_walletrecharge_submit)})
    private void clickEvent(View view) {


        if (checkPass()) {


            progressDialogUtils.showProgress();

            Map<String,String> map = new HashMap<String,String >();
            String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

            map.put("username",username);
            map.put("balance",et_walletrecharge_money.getText().toString());

            HttpUtils.post(ConstantsUrl.userWalletRecharge, map, new OnHttpPostListener() {
                @Override
                public void onSuccess(JSONObject result) {
                    progressDialogUtils.dismissProgress();
                    try {
                        if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                                ToastUtils.showToast("充值成功！");
                                finish();

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



    private boolean checkPass() {


        if (et_walletrecharge_money.getText().toString().equals("0") || TextUtils.isEmpty(et_walletrecharge_money.getText().toString())) {
            ToastUtils.showToast("请输入充值金额！");
            return false;
        }


        if (Integer.parseInt(et_walletrecharge_money.getText().toString()) > 1000) {
            ToastUtils.showToast("单笔最大充值金额为1000元！");
            return false;
        }


        return true;

    }


}
