package com.dev4free.devbuyandroidclient.activity.main3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.ChangeOrderAmountListener;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.activity.main4.AddressManageActivity;
import com.dev4free.devbuyandroidclient.adapter.OrderSubmitFromShoppingCarAdapter;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.entity.ShoppingCarItems;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.MathUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
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
 * Created by syd on 2016/5/12.
 */
public class OrderSubmitFromShoppingCarActivity extends BaseActivity {

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    private int price;
    private int inventory;

    private List<ShoppingCarItems> goodslist;
    private OrderSubmitFromShoppingCarAdapter mOrderSubmitFromShoppingCarAdapter;
    private String address_id;
    private String itemsIdAndNum;


    @ViewInject(R.id.rl_ordersubmitfromshoppingcar_address)
    RelativeLayout rl_ordersubmitfromshoppingcar_address;
    @ViewInject(R.id.tv_ordersubmitfromshoppingcar_address_name)
    TextView tv_ordersubmitfromshoppingcar_address_name;
    @ViewInject(R.id.tv_ordersubmitfromshoppingcar_address_phone)
    TextView tv_ordersubmitfromshoppingcar_address_phone;
    @ViewInject(R.id.tv_ordersubmitfromshoppingcar_address_detail_address)
    TextView tv_ordersubmitfromshoppingcar_address_detail_address;


    @ViewInject(R.id.tv_ordersubmitfromshoppingcar_alltotalmoney)
    TextView tv_ordersubmitfromshoppingcar_alltotalmoney;
    @ViewInject(R.id.btn_ordersubmitfromshoppingcar_submitorder)
    Button btn_ordersubmitfromshoppingcar_submitorder;


    @ViewInject(R.id.lv_ordersubmitfromshoppingcar)
    ListView lv_ordersubmitfromshoppingcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_ordersubmitformshoppingcard);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initTitle("订单");


    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddresses();
        getGoods();
    }

    @Event(value = {R.id.rl_ordersubmitfromshoppingcar_address,R.id.btn_ordersubmitfromshoppingcar_submitorder})
    private void clickEvent(View view) {
        Intent intent = null;
        switch (view.getId()) {



            //地址
            case R.id.rl_ordersubmitfromshoppingcar_address:
                intent = new Intent(mContext, AddressManageActivity.class);
                startActivity(intent);
                break;


            //提交订单
            case R.id.btn_ordersubmitfromshoppingcar_submitorder:


                submitOrder();


                break;



        }

    }


    /**
     * 提交订单
     */
    private void submitOrder() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("address_id",address_id);
        map.put("itemsIdAndNum",itemsIdAndNum);

        HttpUtils.post(ConstantsUrl.submitOrders, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();

                if ("Fragment_main3".equals(getIntent().getStringExtra("fromActivity"))) {

                    deleteShoppingCar(getIntent().getStringExtra("cart_ids"));
                }

                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        ToastUtils.showToast("提交订单成功！");
                        String orders_id = result.optString("content");
                        Intent intent = new Intent(mContext,PaymentActivity.class);
                        intent.putExtra("orders_id",orders_id);
                        intent.putExtra("price",tv_ordersubmitfromshoppingcar_alltotalmoney.getText().toString());
                        startActivity(intent);

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
     * 从购物车入口，提交的订单，提交后需要删除购物车
     * @param cart_ids
     */
    private void deleteShoppingCar(String cart_ids) {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("cart_ids",cart_ids);

        HttpUtils.post(ConstantsUrl.deleteItemsFromShoppingCart, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {


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
     * 获取收获地址
     */
    public void getAddresses() {



        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("default_address","0");

        HttpUtils.post(ConstantsUrl.findAddressByUserName, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {
                        JSONArray jsonArray = result.getJSONArray("content");
                        if (jsonArray.length() > 0) {
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            tv_ordersubmitfromshoppingcar_address_name.setText("收货人：" + jsonObject.optString("consignee_name"));
                            tv_ordersubmitfromshoppingcar_address_phone.setText(jsonObject.optString("phone_number"));
                            tv_ordersubmitfromshoppingcar_address_detail_address.setText(jsonObject.optString("province") + jsonObject.optString("city") + jsonObject.optString("detail_address"));
                            address_id = jsonObject.optString("address_id");
                        }

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


    public void getGoods() {

        //data
        goodslist = (List<ShoppingCarItems>)getIntent().getSerializableExtra("goodslist");

        //adapter
        mOrderSubmitFromShoppingCarAdapter = new OrderSubmitFromShoppingCarAdapter(goodslist, mContext, new ChangeOrderAmountListener() {
            @Override
            public void orderAmountChanged(List<ShoppingCarItems> mGoodsList) {
                goodslist = mGoodsList;
                mOrderSubmitFromShoppingCarAdapter.notifyDataSetChanged();
                calculateTotalMoney();
                generateItemsIdAndNumList();
            }
        });

        //bind
        lv_ordersubmitfromshoppingcar.setAdapter(mOrderSubmitFromShoppingCarAdapter);

        calculateTotalMoney();
        generateItemsIdAndNumList();

    }


    /**
     * 计算订单总金额
     */
    private void calculateTotalMoney() {
        int goodsAmount = 0;
        double goodsPrice = 0;
        for (ShoppingCarItems shoppingCarItems : goodslist) {
            goodsPrice = goodsPrice + MathUtils.number2dot2(Double.parseDouble(shoppingCarItems.getItems_num()) * Double.parseDouble(shoppingCarItems.getCurrent_price()));
            goodsAmount = goodsAmount + Integer.parseInt(shoppingCarItems.getItems_num());
        }
        tv_ordersubmitfromshoppingcar_alltotalmoney.setText(goodsPrice + "");
    }


    /**
     * 生成提交订单所需要的itemsIdAndNum
     * 它是SONArray格式的字符串
     */

    private void generateItemsIdAndNumList() {

        List<Map<String,String>> listMap = new ArrayList<Map<String, String>>() ;
        for (ShoppingCarItems shoppingCarItems : goodslist) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("items_id",shoppingCarItems.getItems_id());
            map.put("items_num",shoppingCarItems.getItems_num());
            listMap.add(map);
        }

        itemsIdAndNum = new Gson().toJson(listMap);

    }
}
