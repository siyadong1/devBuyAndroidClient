package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.AlertInterface;
import com.dev4free.devbuyandroidclient.Interface.DeleteLShoppingCardItemistener;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main3.OrderSubmitFromShoppingCarActivity;
import com.dev4free.devbuyandroidclient.adapter.ShoppingCarAdapter;
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
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main3 extends BaseFragment{




    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    List<ShoppingCarItems> goodsList = new ArrayList<ShoppingCarItems>();
    ShoppingCarAdapter shoppingCarAdapter;
    DeleteLShoppingCardItemistener mDeleteLShoppingCardItemistener;
    @ViewInject(R.id.lv_main3)
    ListView lv_main3;
    @ViewInject(R.id.tv_main3_total_amount)
    TextView tv_main3_total_amount;
    @ViewInject(R.id.tv_main3_total_money)
    TextView tv_main3_total_money;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main3,null);
        mContext = getActivity();
        x.view().inject(this,view);
        progressDialogUtils = new ProgressDialogUtils(mContext);
        ((TextView)view.findViewById(R.id.tv_title_content)).setText("购物车");
        mDeleteLShoppingCardItemistener = new DeleteLShoppingCardItemistener() {
            @Override
            public void deleteShoppingCardItem(final String cart_id) {
                    AlertDialogUtils.showAlertDialog(mContext, "确定删除该条商品？", "取消", "确定", new AlertInterface() {
                        @Override
                        public void confirm(AlertDialog alertDialog) {
                            deleteShoppingCard(cart_id);
                            alertDialog.dismiss();
                        }
                    });
            }
        };
        //data
        getGoods();
        return view;

    }

    /**
     * 删除商品信息
     */
    private void deleteShoppingCard(String cart_id) {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("cart_id",cart_id);

        HttpUtils.post(ConstantsUrl.deleteItemsFromShoppingCart, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        ToastUtils.showToast("删除购物车成功！");
                        getGoods();

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
     * 获取商户购买的商品
     */
    public void getGoods() {



        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);

        HttpUtils.post(ConstantsUrl.findShoppingCartDetailsByUserName, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {


                        goodsList = new Gson().fromJson(result.getJSONArray("content").toString(),new TypeToken<List<ShoppingCarItems>>(){}.getType());
                        //adapter
                        shoppingCarAdapter = new ShoppingCarAdapter(mContext,goodsList,mDeleteLShoppingCardItemistener);
                        //bind
                        lv_main3.setAdapter(shoppingCarAdapter);

                        int goodsAmount = 0;
                        double goodsPrice = 0;
                        for (ShoppingCarItems shoppingCarItems : goodsList) {
                            goodsPrice = goodsPrice + MathUtils.number2dot2(Double.parseDouble(shoppingCarItems.getItems_num()) * Double.parseDouble(shoppingCarItems.getCurrent_price()));
                            goodsAmount = goodsAmount + Integer.parseInt(shoppingCarItems.getItems_num());
                        }
                        tv_main3_total_amount.setText("共" + goodsAmount + "件商品");
                        tv_main3_total_money.setText(goodsPrice + "");

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



    @Event(R.id.btn_main3_settle)
    private void clickEvent(View view) {

        Intent intent = null;

        switch (view.getId()) {

            case R.id.btn_main3_settle:


                if (goodsList.size() == 0) {
                    ToastUtils.showToast("暂无商品哦！");
                } else {
                    intent = new Intent(mContext, OrderSubmitFromShoppingCarActivity.class);
                    intent.putExtra("goodslist", (Serializable) goodsList);
                    intent.putExtra("fromActivity","Fragment_main3");
                    List<String> list = new ArrayList<>();
                    for (ShoppingCarItems shoppingCarItems : goodsList) {
                        list.add(shoppingCarItems.getCart_id());
                    }
                    JSONArray jsonArray = new JSONArray(list);
                    intent.putExtra("cart_ids",jsonArray.toString());
                    startActivity(intent);
                }


                break;

        }


    }

}
