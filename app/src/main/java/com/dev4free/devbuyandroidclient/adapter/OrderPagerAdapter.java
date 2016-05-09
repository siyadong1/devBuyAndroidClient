package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

import com.dev4free.devbuyandroidclient.R;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class OrderPagerAdapter extends FragmentPagerAdapter {


    Context mContext;
    List<Fragment> fragmentsList;



    public OrderPagerAdapter(FragmentManager fm,Context mContext,List<Fragment> fragmentsList) {
        super(fm);

        this.mContext = mContext;
        this.fragmentsList = fragmentsList;

    }


    @Override
    public Fragment getItem(int position) {

        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {

        return fragmentsList.size();
    }
}
