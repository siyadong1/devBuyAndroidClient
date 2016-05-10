package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.adapter.OrderAdapter;
import com.dev4free.devbuyandroidclient.entity.Goods;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class Fragment_order1 extends Fragment{


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    List<Goods> goodsList = new ArrayList<Goods>();
    OrderAdapter orderAdapter;


    @ViewInject(R.id.lv_order1)
    ListView lv_order1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {





        View view = inflater.inflate(R.layout.fragment_order1,container,false);


        x.view().inject(this,view);
        mContext = getActivity();
        progressDialogUtils = new ProgressDialogUtils(mContext);
        //data
        getAllOrders();
        //adapter
        orderAdapter = new OrderAdapter(mContext,goodsList);
        //bind
        lv_order1.setAdapter(orderAdapter);

        return view;







    }

    /**
     * 获取全部订单
     */
    public void getAllOrders() {

        for (int i = 0; i <1 ; i++) {

            Goods goods = new Goods("小米4手机 小米4移动4G 5.0英寸/3GB内存 特别版 运行2GB内存 可选","34","35-128","小米手机旗舰店");
            goodsList.add(goods);

        }


    }
}
