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

public class Fragment_main2 extends BaseFragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main2,null);

        ((TextView)view.findViewById(R.id.tv_title_content)).setText("分类");


        return view;

    }
}
