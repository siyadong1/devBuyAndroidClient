package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev4free.devbuyandroidclient.Interface.DeleteLShoppingCardItemistener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.ShoppingCarItems;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class ShoppingCarAdapter extends BaseAdapter{


    Context mContext;
    List<ShoppingCarItems> goodsList;
    DeleteLShoppingCardItemistener mDeleteLShoppingCardItemistener;

    public ShoppingCarAdapter(Context mContext, List<ShoppingCarItems> goodsList,DeleteLShoppingCardItemistener mDeleteLShoppingCardItemistener) {
        this.goodsList = goodsList;
        this.mContext = mContext;
        this.mDeleteLShoppingCardItemistener = mDeleteLShoppingCardItemistener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

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
        viewHolder.tv_main3_shopname.setText(goodsList.get(position).getItemsname());
        viewHolder.tv_main3_description.setText(goodsList.get(position).getDescription());
        viewHolder.tv_main3_goods_current_price.setText("￥" + goodsList.get(position).getCurrent_price());
        viewHolder.tv_main3_goods_old_price.setText("￥" + goodsList.get(position).getPrice());
        Glide.with(mContext).load(goodsList.get(position).getImage()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_main3_goods_pic);
        viewHolder.btn_main3_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeleteLShoppingCardItemistener.deleteShoppingCardItem(goodsList.get(position).getCart_id());
            }
        });
        return convertView;
    }




    class ViewHolder {

        @ViewInject(R.id.tv_main3_goods_name)
        TextView tv_main3_shopname;
        @ViewInject(R.id.tv_main3_goods_desc)
        TextView tv_main3_description;
        @ViewInject(R.id.tv_main3_goods_current_price)
        TextView tv_main3_goods_current_price;
        @ViewInject(R.id.tv_main3_goods_old_price)
        TextView tv_main3_goods_old_price;
        @ViewInject(R.id.iv_main3_goods_pic)
        ImageView iv_main3_goods_pic;
        @ViewInject(R.id.btn_main3_delete)
        Button btn_main3_delete;
    }

}
