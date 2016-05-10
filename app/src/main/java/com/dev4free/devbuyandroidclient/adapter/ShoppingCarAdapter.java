package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.Goods;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class ShoppingCarAdapter extends BaseAdapter{


    Context mContext;
    List<Goods> goodsList;


    public ShoppingCarAdapter(Context mContext, List<Goods> goodsList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_main3_item, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//
        viewHolder.ck_main3_shopname.setText(goodsList.get(position).getShopName());
        viewHolder.tv_main3_description.setText(goodsList.get(position).getDescription());
        viewHolder.tv_main3_realprice.setText("￥" + goodsList.get(position).getRealPrice());
        viewHolder.tv_main3_saleprice.setText("￥" + goodsList.get(position).getSalesPrice());
        return convertView;
    }



    class ViewHolder {

        @ViewInject(R.id.ck_main3_shopname)
        CheckBox ck_main3_shopname;
        @ViewInject(R.id.tv_main3_description)
        TextView tv_main3_description;
        @ViewInject(R.id.tv_main3_realprice)
        TextView tv_main3_realprice;
        @ViewInject(R.id.tv_main3_saleprice)
        TextView tv_main3_saleprice;

    }

}
