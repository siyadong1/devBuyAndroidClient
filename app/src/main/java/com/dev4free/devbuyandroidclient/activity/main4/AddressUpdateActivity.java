package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

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
 * Created by syd on 2016/5/9.
 */
public class AddressUpdateActivity extends BaseActivity {


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    String address_id;



    @ViewInject(R.id.et_addressupdate_consignee)
    EditText et_addressupdate_consignee;
    @ViewInject(R.id.et_addressupdate_phone)
    EditText et_addressupdate_phone;
    @ViewInject(R.id.et_addressupdate_province)
    EditText et_addressupdate_province;
    @ViewInject(R.id.et_addressupdate_city)
    EditText et_addressupdate_city;
    @ViewInject(R.id.et_addressupdate_detail)
    EditText et_addressupdate_detail;
    @ViewInject(R.id.rb_addressupdate_default)
    RadioButton rb_addressupdate_default;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addressupdate);
        initTitle("更新地址");

        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initView();
    }

    private void initView() {

        address_id = getIntent().getStringExtra("address_id");
        String consignee_name = getIntent().getStringExtra("consignee_name");
        String phone_number = getIntent().getStringExtra("phone_number");
        String province = getIntent().getStringExtra("province");
        String city = getIntent().getStringExtra("city");
        String detail_address = getIntent().getStringExtra("detail_address");
        String default_address = getIntent().getStringExtra("default_address");

        et_addressupdate_consignee.setText(consignee_name);
        et_addressupdate_phone.setText(phone_number);
        et_addressupdate_province.setText(province);
        et_addressupdate_city.setText(city);
        et_addressupdate_detail.setText(detail_address);
        if ("0".equals(default_address)) {
            rb_addressupdate_default.setChecked(true);
        } else {
            rb_addressupdate_default.setChecked(false);
        }

    }


    @Event(R.id.btn_addressupdate_confirm)
    private void clickEvent(View view) {

        if (checkPass()) {
            updateCityAddress();
        }



    }


    /**
     * 添加地址
     */
    private void updateCityAddress() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");
        String consignee_name = et_addressupdate_consignee.getText().toString();
        String phone_number = et_addressupdate_phone.getText().toString();
        String province = et_addressupdate_province.getText().toString();
        String city = et_addressupdate_city.getText().toString();
        String detail_address = et_addressupdate_detail.getText().toString();
        String default_address = "0";
        if (!rb_addressupdate_default.isChecked()) {
            default_address = "1";
        }
        map.put("address_id",address_id);
        map.put("username",username);
        map.put("consignee_name",consignee_name);
        map.put("phone_number",phone_number);
        map.put("province",province);
        map.put("city",city);
        map.put("detail_address",detail_address);
        map.put("default_address",default_address);

        HttpUtils.post(ConstantsUrl.updateShippingAddress, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        ToastUtils.showToast("更新地址成功！");
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

    /**
     * 检查地址填写是否为空
     */
    private boolean checkPass() {

        if (TextUtils.isEmpty(et_addressupdate_consignee.getText().toString())) {
            ToastUtils.showToast("收货人不能为空！");
            return false;
        }

        if (TextUtils.isEmpty(et_addressupdate_phone.getText().toString())) {
            ToastUtils.showToast("联系电话不能为空！");
            return false;
        }

        if (TextUtils.isEmpty(et_addressupdate_province.getText().toString())) {
            ToastUtils.showToast("所在省不能为空！");
            return false;
        }

        if (TextUtils.isEmpty(et_addressupdate_city.getText().toString())) {
            ToastUtils.showToast("所在市不能为空！");
            return false;
        }
        if (TextUtils.isEmpty(et_addressupdate_city.getText().toString())) {
            ToastUtils.showToast("详细地址不能为空！");
            return false;
        }

        return true;
    }

}
