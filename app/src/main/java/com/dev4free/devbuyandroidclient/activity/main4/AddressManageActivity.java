package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.adapter.AddressAdapter;
import com.dev4free.devbuyandroidclient.entity.Address;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class AddressManageActivity extends BaseActivity {


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    List<Address> addressLists = new ArrayList<Address>();
    AddressAdapter mAddressAdapter;

    
    @ViewInject(R.id.tv_manageaddress_name)
    TextView tv_manageaddress_name;
    @ViewInject(R.id.tv_manageaddress_phone)
    TextView tv_manageaddress_phone;
    @ViewInject(R.id.tv_manageaddress_address)
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


        //data
        getAddresses();
        //adapter
        mAddressAdapter = new AddressAdapter(mContext,addressLists);
        //bind
        lv_manageaddress.setAdapter(mAddressAdapter);


    }


    public void getAddresses() {


    Address mAddress = new Address("斯亚东","15228938451","浙江省杭州市滨江区莲花乡池水沟子8存8组1户五栋99楼1号2号房间上铺","1");
    addressLists.add(mAddress);
        for (int i = 0; i <2 ; i++) {
            Address mAddress1 = new Address("斯亚东","15228938451","浙江省杭州市滨江区莲花乡池水沟子8存8组1户五栋99楼1号2号房间上铺","0");
            addressLists.add(mAddress1);

        }

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
