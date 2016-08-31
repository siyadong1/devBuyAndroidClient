package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.NavigatarBean;

import java.util.List;

/**
 * Created by syd on 2016/5/13.
 */
public class Main1NavigatorAdapter extends BaseAdapter {

    List<NavigatarBean> navigatorList;
    Context mContext;


    public Main1NavigatorAdapter(Context mContext, List<NavigatarBean> navigatorList) {
        this.mContext = mContext;
        this.navigatorList = navigatorList;
    }

    @Override
    public int getCount() {
        return navigatorList.size();
    }

    @Override
    public Object getItem(int position) {
        return navigatorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView  = LayoutInflater.from(mContext).inflate(R.layout.adapter_fragment1_navigator,null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_fragment1_navigator);
        Glide.with(mContext).load(navigatorList.get(position).getCf_url()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(imageView);


        return convertView;
    }
}
