package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.GoodsListBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/28.
 */
public class GoodsListAdapter extends BaseAdapter {

    Context mContext;
    List<GoodsListBean> listData;

    public GoodsListAdapter(Context mContext,List<GoodsListBean> listData) {
        this.listData = listData;
        this.mContext = mContext;
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

        ViewHolder viewHolder = null;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_goodslist_item,null);
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(listData.get(position).getImage()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_goodslist);
        viewHolder.tv_goodslist_description.setText(listData.get(position).getDescription());
        viewHolder.tv_goodslist_amount.setText(listData.get(position).getSales_volume());
        viewHolder.tv_goodslist_realprice.setText("￥" + listData.get(position).getPrice());
        viewHolder.tv_goodslist_realprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        viewHolder.tv_goodslist_salesprice.setText("￥" + listData.get(position).getCurrent_price());

        return convertView;

    }



    class ViewHolder {

        @ViewInject(R.id.iv_goodslist)
        ImageView iv_goodslist;
        @ViewInject(R.id.tv_goodslist_description)
        TextView tv_goodslist_description;
        @ViewInject(R.id.tv_goodslist_realprice)
        TextView tv_goodslist_realprice;
        @ViewInject(R.id.tv_goodslist_amount)
        TextView tv_goodslist_amount;
        @ViewInject(R.id.tv_goodslist_salesprice)
        TextView tv_goodslist_salesprice;

    }

}
