package com.dev4free.devbuyandroidclient.activity.main2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.adapter.GoodsListAdapter;
import com.dev4free.devbuyandroidclient.entity.Goods;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/5/28.
 */
public class GoodsList extends BaseActivity implements AdapterView.OnItemClickListener{

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    private List<Goods> listData = new ArrayList<Goods>();

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
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_1_1_1));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_1_1_2));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_1_1_3));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone__grid_1_2_1));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_1_2_2));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_1_2_3));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_2_1_1));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_2_1_2));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_2_1_3));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_2_2_1));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_2_2_2));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_3_1_1));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_3_1_2));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_3_1_3));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_3_2_1));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_3_2_2));
        listData.add(new Goods("小米4手机 小米4移动 4G 5.0英寸/3GB内存 特别版运行2GB内存 可选","￥1941","6952","￥998",R.mipmap.category_phone_grid_3_2_3));

        //adapter
        goodsListAdapter = new GoodsListAdapter(mContext,listData);

        //bind
        gv_goodslist.setAdapter(goodsListAdapter);

        //click
        gv_goodslist.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(mContext,GoodsDetail.class);
        startActivity(intent);

    }
}
