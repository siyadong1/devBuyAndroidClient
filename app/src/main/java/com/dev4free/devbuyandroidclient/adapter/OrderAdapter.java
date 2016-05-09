package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.Goods;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class OrderAdapter extends BaseAdapter{


    Context mContext;
    List<Goods> goodsList;


    public OrderAdapter(Context mContext, List<Goods> goodsList) {
        this.goodsList = goodsList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsList.get(position) ;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_order1_item, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_order1_shopname.setText(goodsList.get(position).getShopName());
        viewHolder.tv_order1_description.setText(goodsList.get(position).getDescription());
        viewHolder.tv_order1_realprice.setText("￥" + goodsList.get(position).getRealPrice());
        viewHolder.tv_order1_saleprice.setText("￥" + goodsList.get(position).getSalesPrice());
        viewHolder.tv_order1_saleprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        return convertView;
    }



    class ViewHolder {

        @ViewInject(R.id.tv_order1_shopname)
        TextView tv_order1_shopname;
        @ViewInject(R.id.tv_order1_description)
        TextView tv_order1_description;
        @ViewInject(R.id.tv_order1_realprice)
        TextView tv_order1_realprice;
        @ViewInject(R.id.tv_order1_saleprice)
        TextView tv_order1_saleprice;

    }

}
