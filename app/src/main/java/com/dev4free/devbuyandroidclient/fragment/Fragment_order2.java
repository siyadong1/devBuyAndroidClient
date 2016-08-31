package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dev4free.devbuyandroidclient.Interface.AlertInterface;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.Interface.OrderCancelListener;
import com.dev4free.devbuyandroidclient.Interface.OrderPaymentListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main3.PaymentActivity;
import com.dev4free.devbuyandroidclient.adapter.Order2Adapter;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsOrderState;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.entity.OrderGoods;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syd on 2016/5/9.
 */
public class Fragment_order2 extends Fragment{


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    OrderPaymentListener mOrderPaymentListener;
    OrderCancelListener mOrderCancelListener;


    List<OrderGoods> goodsList = new ArrayList<OrderGoods>();
    Order2Adapter order2Adapter;

    boolean isCreate = false;

    @ViewInject(R.id.lv_order2)
    ListView lv_order2;
    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order2,container,false);
        x.view().inject(this,view);

        mContext = getActivity();
        progressDialogUtils = new ProgressDialogUtils(mContext);
        mOrderPaymentListener = new OrderPaymentListener() {
            @Override
            public void orderPayment(String orders_id,String sum) {

                submitOrder(orders_id,sum);

            }
        };
        mOrderCancelListener = new OrderCancelListener() {
            @Override
            public void orderCancel(final String orders_id) {


                AlertDialogUtils.showAlertDialog(mContext, "确定取消订单", "取消", "确定", new AlertInterface() {
                    @Override
                    public void confirm(AlertDialog alertDialog) {
                        cancelOrder(orders_id);
                        alertDialog.dismiss();
                    }
                });

            }
        };


        if (!isCreate) {
            //data
            getAllOrders();
        } else {
            //adapter
            order2Adapter = new Order2Adapter(mContext,goodsList,mOrderCancelListener,mOrderPaymentListener);
            //bind
            lv_order2.setAdapter(order2Adapter);

        }



        return view;







    }


    /**
     * 提交订单
     * @param orders_id
     */
    private void submitOrder(String orders_id,String sum) {


        Intent intent = new Intent(mContext, PaymentActivity.class);
        intent.putExtra("orders_id",orders_id);
        intent.putExtra("price",sum);
        startActivity(intent);


    }


    /**
     * 删除订单
     */
    private void cancelOrder(String orders_id) {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("orders_id",orders_id);

        HttpUtils.post(ConstantsUrl.cancelOrdersByOrdersId, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        ToastUtils.showToast("订单取消成功！");
                        getAllOrders();

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
     * 获取全部订单
     */
    public void getAllOrders() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("state", ConstantsOrderState.orderState0);

        HttpUtils.post(ConstantsUrl.findOrdersByUserName, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        JSONArray jsonArrayOrder = result.getJSONArray("content");
                        goodsList = new ArrayList<OrderGoods>();
                        if (jsonArrayOrder != null && jsonArrayOrder.length() > 0) {
                            for (int i = 0; i <jsonArrayOrder.length() ; i++) {
                                JSONArray jSONArrayOrderDetailCustom = jsonArrayOrder.getJSONObject(i).getJSONArray("orderDetailCustom");
                                if (jSONArrayOrderDetailCustom != null && jSONArrayOrderDetailCustom.length() > 0) {

                                    for (int j = 0; j < jSONArrayOrderDetailCustom.length(); j++) {
                                        JSONObject jSONObjectOrderDetailCustom = jSONArrayOrderDetailCustom.getJSONObject(j);
                                        OrderGoods orderGoods = new OrderGoods();
                                        orderGoods.setItems_num(jSONObjectOrderDetailCustom.optString("items_num"));
                                        JSONObject jSONObjectOrderDetailCustomItems = jSONObjectOrderDetailCustom.getJSONObject("items");
                                        orderGoods.setImage(jSONObjectOrderDetailCustomItems.optString("image"));
                                        orderGoods.setPrice(jSONObjectOrderDetailCustomItems.optString("price"));
                                        orderGoods.setItemsname(jSONObjectOrderDetailCustomItems.optString("itemsname"));
                                        orderGoods.setCurrent_price(jSONObjectOrderDetailCustomItems.optString("current_price"));
                                        orderGoods.setDescription(jSONObjectOrderDetailCustomItems.optString("description"));
                                        orderGoods.setInventory(jSONObjectOrderDetailCustomItems.optString("inventory"));
                                        orderGoods.setOrders_id(jsonArrayOrder.getJSONObject(i).optString("orders_id"));
                                        orderGoods.setSum(jsonArrayOrder.getJSONObject(i).optString("sum"));
                                        orderGoods.setState(jsonArrayOrder.getJSONObject(i).optString("state"));

                                        goodsList.add(orderGoods);
                                    }
                                }

                            }

                        }

                        isCreate = true;
                        //adapter
                        order2Adapter = new Order2Adapter(mContext,goodsList,mOrderCancelListener,mOrderPaymentListener);
                        //bind
                        lv_order2.setAdapter(order2Adapter);

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
