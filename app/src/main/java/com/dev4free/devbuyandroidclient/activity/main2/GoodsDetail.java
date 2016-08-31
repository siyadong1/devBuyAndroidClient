package com.dev4free.devbuyandroidclient.activity.main2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.activity.main3.OrderSubmitFromShoppingCarActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.entity.ShoppingCarItems;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syd on 2016/5/28.
 */
public class GoodsDetail extends BaseActivity {


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    private String items_id;
    String image;
    String itemsname;
    String description;
    String current_price;
    String price;
    String express_fee;
    String sales_volume;
    String area ;
    String inventory ;

    private List<ShoppingCarItems> goodslist;





    @ViewInject(R.id.iv_goodsdetail_image)
    ImageView iv_goodsdetail_image;


    @ViewInject(R.id.tv_goodsdetail_name)
    TextView tv_goodsdetail_name;
    @ViewInject(R.id.tv_goodsdetail_desc)
    TextView tv_goodsdetail_desc;
    @ViewInject(R.id.tv_goodsdetail_price_now)
    TextView tv_goodsdetail_price_now;
    @ViewInject(R.id.tv_goodsdetail_price_old)
    TextView tv_goodsdetail_price_old;
    @ViewInject(R.id.tv_goodsdetail_express_fee)
    TextView tv_goodsdetail_express_fee;
    @ViewInject(R.id.tv_goodsdetail_sales_volume)
    TextView tv_goodsdetail_sales_volume;
    @ViewInject(R.id.tv_goodsdetail_area)
    TextView tv_goodsdetail_area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);
        initTitle("商品详情");

        tv_goodsdetail_price_old.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        items_id = getIntent().getStringExtra("items_id");
        getGoodsDetail();

    }


    @Event(value = {R.id.btn_goodsdeail_gotoshoppingpcar,R.id.btn_goodsdeail_gotobuy})
    private void clickEvent(View view) {
        Intent intent = null;
        switch (view.getId()) {


            case R.id.btn_goodsdeail_gotoshoppingpcar:

               addToShoppingCar();

                break;


            case R.id.btn_goodsdeail_gotobuy:

                intent = new Intent(mContext, OrderSubmitFromShoppingCarActivity.class);
                intent.putExtra("goodslist", (Serializable) goodslist);
                startActivity(intent);
                break;



        }



    }

    private void addToShoppingCar() {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);
        map.put("items_id",items_id);
        HttpUtils.post(ConstantsUrl.appendItemsToShoppingCart, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        ToastUtils.showToast("已成功加入购物车！");
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

    public void getGoodsDetail() {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();

        map.put("items_id",items_id);

        HttpUtils.post(ConstantsUrl.findItemsByItemsId, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {
                        JSONObject content = result.getJSONObject("content");


                         image= content.optString("image");
                         itemsname = content.optString("itemsname");
                         description = content.optString("description");
                         current_price = content.optString("current_price");
                         price = content.optString("price");
                         express_fee = content.optString("express_fee");
                         sales_volume = content.optString("sales_volume");
                         area = content.optString("area");
                        inventory = content.optString("inventory");


                        ShoppingCarItems shoppingCarItems = new ShoppingCarItems(current_price,description,image,inventory,itemsname,price,"1", content.optString("items_id"));
                        goodslist = new ArrayList<ShoppingCarItems>();
                        goodslist.add(shoppingCarItems);

                        Glide.with(mContext).load(image).centerCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).crossFade().into(iv_goodsdetail_image);
                        tv_goodsdetail_name.setText(itemsname);
                        tv_goodsdetail_desc.setText(description);
                        tv_goodsdetail_price_now.setText(current_price);
                        tv_goodsdetail_price_old.setText(price);
                        if ("0".equals(express_fee)) {
                            tv_goodsdetail_express_fee.setText("免费");
                        } else {
                            tv_goodsdetail_express_fee.setText(express_fee);
                        }
                        tv_goodsdetail_sales_volume.setText("月销" + sales_volume + "笔");
                        tv_goodsdetail_area.setText(area);

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
