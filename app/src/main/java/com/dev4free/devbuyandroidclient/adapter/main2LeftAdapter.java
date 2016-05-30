package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/27.
 */
public class Main2LeftAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> listDate;


    public Main2LeftAdapter(Context mContext , List<String> listDate) {
        this.listDate = listDate;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return listDate.size();
    }

    @Override
    public Object getItem(int position) {
        return listDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_main2_left_item, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tv_main2_left.setText(listDate.get(position));
        if (listDate.get(position).equals("图书")) {
            viewHolder.tv_main2_left.setTextColor(mContext.getResources().getColor(R.color.red));
        } else {
            viewHolder.tv_main2_left.setTextColor(Color.rgb(70,70,70));
        }

        return convertView;
    }



    class ViewHolder {

        @ViewInject(R.id.tv_main2_left)
        TextView tv_main2_left;


    }

}
