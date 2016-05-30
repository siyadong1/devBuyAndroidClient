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
public class Fragment_main2_right1 extends BaseFragment implements AdapterView.OnItemClickListener{



    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    Main2RightAdapter rightAdapter1;
    Main2RightAdapter rightAdapter2;
    Main2RightAdapter rightAdapter3;

    private List<Main2GoodsAndName> listDataRight1 = new ArrayList<Main2GoodsAndName>();
    private List<Main2GoodsAndName> listDataRight2 = new ArrayList<Main2GoodsAndName>();
    private List<Main2GoodsAndName> listDataRight3 = new ArrayList<Main2GoodsAndName>();




    @ViewInject(R.id.gv_main2_right1_1)
    GridView gv_main2_right1_1;
    @ViewInject(R.id.gv_main2_right1_2)
    GridView gv_main2_right1_2;
    @ViewInject(R.id.gv_main2_right1_3)
    GridView gv_main2_right1_3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main2_right1,null);
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
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_book_grid_1_1_1,"十族隐身"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_book_grid_1_1_2,"诛仙"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_book_grid_1_1_3,"龙族III"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_book_grid_1_2_1,"没有来生"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_book_grid_1_2_2,"光之子"));
        listDataRight1.add(new Main2GoodsAndName(R.mipmap.list_book_grid_1_2_3,"其它"));


        //data2
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_book_grid_2_1_1,"彬彬来吃"));
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_book_grid_2_1_2,"奢侈"));
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_book_grid_2_1_3,"蜗居"));
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_book_grid_2_2_1,"单恋"));
        listDataRight2.add(new Main2GoodsAndName(R.mipmap.list_book_grid_2_2_2,"你会想念自己吗"));

        //data3
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_book_grid_3_2_3,"情节"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_book_grid_3_1_2,"御宅"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_book_grid_3_1_3,"美少年"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_book_grid_3_2_1,"黄靖翔"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_book_grid_3_2_2,"萌动漫"));
        listDataRight3.add(new Main2GoodsAndName(R.mipmap.list_book_grid_3_2_3,"爸吗来自二次元"));


        //adapter
        rightAdapter1 = new Main2RightAdapter(mContext,listDataRight1);
        rightAdapter2 = new Main2RightAdapter(mContext,listDataRight2);
        rightAdapter3 = new Main2RightAdapter(mContext,listDataRight3);
        //bind
        gv_main2_right1_1.setAdapter(rightAdapter1);
        gv_main2_right1_2.setAdapter(rightAdapter2);
        gv_main2_right1_3.setAdapter(rightAdapter3);
        //click
        gv_main2_right1_1.setOnItemClickListener(this);
        gv_main2_right1_2.setOnItemClickListener(this);
        gv_main2_right1_3.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = null;

        if ("其它".equals(((TextView)view.findViewById(R.id.tv_main2_right)).getText().toString())) {
            intent = new Intent(mContext, GoodsList.class);
            startActivity(intent);
        } else {
            intent = new Intent(mContext, GoodsDetail.class);
            startActivity(intent);
        }
    }
}
