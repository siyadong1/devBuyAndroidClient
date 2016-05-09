package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.City;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/8.
 */
public class CityAdapter extends BaseAdapter {

    Context mContext;
    List<City> cities;


    public CityAdapter(Context mContext,List<City> cities) {

        this.mContext = mContext;
        this.cities = cities;

    }



    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {

            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_city_item, null);
            x.view().inject(mViewHolder, convertView);
            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.tv_city_symbol.setText(cities.get(position).getSymbol());
        mViewHolder.tv_city_name.setText(cities.get(position).getName());

        if (position > 0 && cities.get(position).getSymbol().equals(cities.get(position - 1).getSymbol())) {
            mViewHolder.tv_city_symbol.setVisibility(View.GONE);
        }

        return convertView;
    }


    private class ViewHolder {
        @ViewInject(R.id.tv_city_symbol)
        TextView tv_city_symbol;
        @ViewInject(R.id.tv_city_name)
        TextView tv_city_name;

    }
}

