package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main1.CityActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main1 extends BaseFragment{


    @ViewInject(R.id.ll_main1_city)
    LinearLayout ll_main1_city;
    Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main1,null);
        mContext = getActivity();
        x.view().inject(this,view);


        return view;
    }

    @Event(R.id.ll_main1_city)
    private void goToCity(View view) {

        Intent intent = new Intent(mContext, CityActivity.class);
        startActivity(intent);

    }

}
