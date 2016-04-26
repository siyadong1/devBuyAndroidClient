package com.dev4free.devbuyandroidclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main4 extends BaseFragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main3,null);

        ((TextView)view.findViewById(R.id.tv_title_content)).setText("我的");


        return view;

    }
}
