package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.Main2GoodsAndName;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/27.
 */
public class Main2RightAdapter extends BaseAdapter {

    private Context mContext;
    private List<Main2GoodsAndName> listDate;


    public Main2RightAdapter(Context mContext , List<Main2GoodsAndName> listDate) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_main2_right_item, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.iv_main2_right.setImageDrawable(mContext.getResources().getDrawable(listDate.get(position).getImage()));
        viewHolder.tv_main2_right.setText(listDate.get(position).getName());

        return convertView;
    }



    class ViewHolder {

        @ViewInject(R.id.iv_main2_right)
        ImageView iv_main2_right;
        @ViewInject(R.id.tv_main2_right)
        TextView tv_main2_right;
    }

}
