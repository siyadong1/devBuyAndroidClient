package com.dev4free.devbuyandroidclient.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.adapter.Main2LeftAdapter;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.view.BladeView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main2 extends BaseFragment implements AdapterView.OnItemClickListener{


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    private List<String> listDataLeft = new ArrayList<String>();
    private int leftIndex = 0;
    Main2LeftAdapter leftAdapter;
    FragmentManager manager;
    FragmentTransaction transaction;

    @ViewInject(R.id.lv_main2_left)
    ListView lv_main2_left;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main2,null);
        x.view().inject(this,view);
        mContext = getActivity();
        progressDialogUtils = new ProgressDialogUtils(mContext);
        view.findViewById(R.id.ll_title_back).setVisibility(View.GONE);
        ((TextView)view.findViewById(R.id.tv_title_content)).setText("分类");
        lv_main2_left.setOnItemClickListener(this);
        initLeft();

        manager = getFragmentManager();
        changeFragment(new Fragment_main2_right1());

        return view;

    }



    private void changeFragment(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main2_right, fragment);
        transaction.commit();

    }


    /**
     * 初始化左边的导航栏
     */
    private void initLeft() {

        //data
        listDataLeft.add("图书");
        listDataLeft.add("手机");
        listDataLeft.add("电脑");
        listDataLeft.add("电器");
        listDataLeft.add("品牌男装");
        listDataLeft.add("品牌女装");
        listDataLeft.add("化妆品");
        listDataLeft.add("鞋子");
        listDataLeft.add("宠物");
        listDataLeft.add("水果");
        listDataLeft.add("零食");


        //adapter
        leftAdapter = new Main2LeftAdapter(mContext,listDataLeft);
        //bind
        lv_main2_left.setAdapter(leftAdapter);

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ((TextView)view.findViewById(R.id.tv_main2_left)).setTextColor(getResources().getColor(R.color.red));



        if (leftIndex != position) {
            ((TextView)(((LinearLayout)parent.getChildAt(leftIndex)).findViewById(R.id.tv_main2_left))).setTextColor(Color.rgb(70,70,70));
            leftIndex = position;
        }

        if (position == 0) {
            changeFragment(new Fragment_main2_right1());
        }
        if (position == 1) {
            changeFragment(new Fragment_main2_right2());
        }


    }
}
