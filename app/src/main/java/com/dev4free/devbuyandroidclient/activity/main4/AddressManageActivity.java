package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.DeleteAddressListener;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.adapter.AddressAdapter;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.entity.Address;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syd on 2016/5/9.
 */
public class AddressManageActivity extends BaseActivity {


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    List<Address> addressLists = new ArrayList<Address>();
    AddressAdapter mAddressAdapter;
    DeleteAddressListener mDeleteAddressListener;
    
    @ViewInject(R.id.tv_ordersubmit_address_name)
    TextView tv_manageaddress_name;
    @ViewInject(R.id.tv_ordersubmit_address_phone)
    TextView tv_manageaddress_phone;
    @ViewInject(R.id.tv_ordersubmit_address_detail_address)
    TextView tv_manageaddress_address;
    @ViewInject(R.id.tv_manageaddress_defaultaddress)
    TextView tv_manageaddress_defaultaddress;
    @ViewInject(R.id.tv_manageaddress_editaddress)
    TextView tv_manageaddress_editaddress;
    @ViewInject(R.id.tv_manageaddress_deleteaddress)
    TextView tv_manageaddress_deleteaddress;
    @ViewInject(R.id.lv_manageaddress)
    ListView lv_manageaddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manageaddress);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);
        initTitle("管理收货地址");
        mDeleteAddressListener = new DeleteAddressListener() {
            @Override
            public void deleteAddress(String address_id) {
                deleteUserAddress(address_id);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        //data
        getAddresses();
    }



    public void deleteUserAddress(String address_id) {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("address_id",address_id);

        HttpUtils.post(ConstantsUrl.deleteShippingAddress, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    getAddresses();
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {
                        ToastUtils.showToast("成功删除！");
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
     * 获取用户地址
     */

    public void getAddresses() {



        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("default_address","1");

        HttpUtils.post(ConstantsUrl.findAddressByUserName, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        addressLists = new Gson().fromJson(result.getJSONArray(ConstantsHttp.CONTENT).toString(),new TypeToken<List<Address>>(){}.getType());
                        //adapter
                        mAddressAdapter = new AddressAdapter(mContext,addressLists,mDeleteAddressListener);
                        //bind
                        lv_manageaddress.setAdapter(mAddressAdapter);
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
     * 处理点击事件
     * @param view
     */
    @Event(value = {R.id.btn_manageaddress_add})
    private void clickEvent(View view) {

        switch (view.getId()) {

            case R.id.btn_manageaddress_add:

                Intent intent = new Intent(mContext,AddressAddActivity.class);
                startActivity(intent);

                break;

        }

    }



}
