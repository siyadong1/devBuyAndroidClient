package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main2.GoodsDetail;
import com.dev4free.devbuyandroidclient.activity.main2.GoodsList;
import com.dev4free.devbuyandroidclient.adapter.Main2RightAdapter;
import com.dev4free.devbuyandroidclient.entity.Main2GoodsAndName;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/5/28.
 */
public class Fragment_main2_right2 extends BaseFragment implements AdapterView.OnItemClickListener{



    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    Main2RightAdapter rightAdapter1;
    Main2RightAdapter rightAdapter2;
    Main2RightAdapter rightAdapter3;

    private List<Main2GoodsAndName> listDataRight1 = new ArrayList<Main2GoodsAndName>();
    private List<Main2GoodsAndName> listDataRight2 = new ArrayList<Main2GoodsAndName>();
    private List<Main2GoodsAndName> listDataRight3 = new ArrayList<Main2GoodsAndName>();




    @ViewInject(R.id.gv_main2_right2_1)
    GridView gv_main2_right2_1;
    @ViewInject(R.id.gv_main2_right2_2)
    GridView gv_main2_right2_2;
    @ViewInject(R.id.gv_main2_right2_3)
    GridView gv_main2_right2_3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main2_right2,null);
        mContext = getActivity();
        x.view().inject(this,view);
        progressDialogUtils = new ProgressDialogUtils(mContext);



        initRight();

        return view;


    }




    /**
     * 初始化右边的内容
     */
    private void initRight() {


        //data1
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_1_1_1,"小米4c"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_1_1_2,"小米Note"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_phone_1_1_3,"小米5"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_phone__grid_1_2_1,"红米3"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_1_2_2,"红米2A"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_1_2_3,"其它"));


        //data2
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_2_1_1,"iPhone SE"));
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_2_1_2,"iPhone 5S"));
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_2_1_3,"6S Plus"));
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_2_2_1,"iPhone 6"));
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_phone_grid_2_2_2,"其它"));

        //data3
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_phone__grid_3_1_1,"华为Mate8"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_phone__grid_3_1_2,"华为 5S"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_phone__grid_3_1_3,"华为P7"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_phone__grid_3_2_1,"华为麦芒4"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_phone__grid_3_2_2,"荣耀7"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_phone__grid_3_2_3,"其它"));


        //adapter
        rightAdapter1 = new Main2RightAdapter(mContext,listDataRight1);
        rightAdapter2 = new Main2RightAdapter(mContext,listDataRight2);
        rightAdapter3 = new Main2RightAdapter(mContext,listDataRight3);
        //bind
        gv_main2_right2_1.setAdapter(rightAdapter1);
        gv_main2_right2_2.setAdapter(rightAdapter2);
        gv_main2_right2_3.setAdapter(rightAdapter3);
        //click
        gv_main2_right2_1.setOnItemClickListener(this);
        gv_main2_right2_2.setOnItemClickListener(this);
        gv_main2_right2_3.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;

        if ("其它".equals(((TextView)view.findViewById(R.id.tv_main2_right)).getText().toString())) {
            intent = new Intent(mContext, GoodsList.class);
            intent.putExtra("category","0");
            startActivity(intent);
        } else {
            intent = new Intent(mContext, GoodsDetail.class);
            intent.putExtra("items_id","1f223a4bef4941b4afeb01f17be8bb65");
            startActivity(intent);
        }
    }
}
