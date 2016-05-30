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
    List<City> listData;


    public CityAdapter(Context mContext,List<City> listData) {

        this.mContext = mContext;
        this.listData = listData;

    }



    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
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

        mViewHolder.tv_city_symbol.setText(listData.get(position).getSymbol());
        mViewHolder.tv_city_name.setText(listData.get(position).getName());

        if (position == getFirstAppearPositionForSymbol(listData.get(position).getSymbol())) {
            mViewHolder.tv_city_symbol.setVisibility(View.VISIBLE);
        } else {
            mViewHolder.tv_city_symbol.setVisibility(View.GONE);

        }

        return convertView;
    }


    /**
     * 返回首次出现标示的位置
     * @param symbol
     * @return
     */
    public int getFirstAppearPositionForSymbol(String symbol) {

        for (int i = 0; i <listData.size(); i++) {

            if (listData.get(i).getSymbol().equals(symbol)) {
                return i;
            }
        }

        return -1;

    }


    private class ViewHolder {
        @ViewInject(R.id.tv_city_symbol)
        TextView tv_city_symbol;
        @ViewInject(R.id.tv_city_name)
        TextView tv_city_name;

    }
}

