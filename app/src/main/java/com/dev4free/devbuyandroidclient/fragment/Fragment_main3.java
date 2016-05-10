package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.adapter.ShoppingCarAdapter;
import com.dev4free.devbuyandroidclient.entity.Goods;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main3 extends BaseFragment{




    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    List<Goods> goodsList = new ArrayList<Goods>();
    ShoppingCarAdapter shoppingCarAdapter;

    @ViewInject(R.id.lv_main3)
    ListView lv_main3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main3,null);
        mContext = getActivity();
        x.view().inject(this,view);
        progressDialogUtils = new ProgressDialogUtils(mContext);


        ((TextView)view.findViewById(R.id.tv_title_content)).setText("购物车");



        //data
        getGoods();
        //adapter
        shoppingCarAdapter = new ShoppingCarAdapter(mContext,goodsList);
        //bind
        lv_main3.setAdapter(shoppingCarAdapter);


        return view;

    }


    /**
     * 获取商户购买的商品
     */
    public void getGoods() {

        for (int i = 0; i <3 ; i++) {

            Goods goods = new Goods("小米4手机 小米4移动4G 5.0英寸/3GB内存 特别版 运行2GB内存 可选","34","35-128","小米手机旗舰店");
            goodsList.add(goods);

        }


    }
}
