package com.dev4free.devbuyandroidclient.activity.main2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.adapter.GoodsListAdapter;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.entity.GoodsListBean;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syd on 2016/5/28.
 */
public class GoodsList extends BaseActivity implements AdapterView.OnItemClickListener{

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    private List<GoodsListBean> listData = new ArrayList<GoodsListBean>();

    GoodsListAdapter goodsListAdapter = null;

    @ViewInject(R.id.gv_goodslist)
    GridView gv_goodslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_goodslist);
        initTitle("商品列表");
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        //data
        getListData();

        //click
        gv_goodslist.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(mContext,GoodsDetail.class);
        intent.putExtra("items_id",listData.get(position).getItems_id());
        startActivity(intent);

    }

    public void getListData() {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("category",getIntent().getStringExtra("category"));

        HttpUtils.post(ConstantsUrl.queryItemsDetailByCategory, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {


                        listData = new Gson().fromJson(result.getJSONArray("content").toString(),new TypeToken<List<GoodsListBean>>(){}.getType());

                        if (listData != null && listData.size() > 0) {
                            //adapter
                            goodsListAdapter = new GoodsListAdapter(mContext, listData);
                            //bind
                            gv_goodslist.setAdapter(goodsListAdapter);
                        } else {
                            ToastUtils.showToast("暂无数据！");
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
}
