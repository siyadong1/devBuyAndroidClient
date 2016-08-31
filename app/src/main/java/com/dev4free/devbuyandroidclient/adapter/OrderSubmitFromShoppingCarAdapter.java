package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev4free.devbuyandroidclient.Interface.ChangeOrderAmountListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.ShoppingCarItems;
import com.dev4free.devbuyandroidclient.utils.MathUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/8/27.
 */
public class OrderSubmitFromShoppingCarAdapter extends BaseAdapter {



    Context mContext;
    List<ShoppingCarItems> goodsList;
    ChangeOrderAmountListener mChangeOrderAmountListener;


    public OrderSubmitFromShoppingCarAdapter(List<ShoppingCarItems> goodsList, Context mContext,ChangeOrderAmountListener mChangeOrderAmountListener) {
        this.goodsList = goodsList;
        this.mContext = mContext;
        this.mChangeOrderAmountListener = mChangeOrderAmountListener;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsList.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_ordersubmitfromshoppingcar_item, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_ordersubmitfromshoppingcar_goods_name.setText(goodsList.get(position).getItemsname());
        viewHolder.tv_ordersubmitfromshoppingcar_goods_desc.setText(goodsList.get(position).getDescription());
        Glide.with(mContext).load(goodsList.get(position).getImage()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_ordersubmitfromshoppingcar_goods_pic);
        viewHolder.tv_ordersubmitfromshoppingcar_goods_old_price.setText(goodsList.get(position).getPrice());
        viewHolder.tv_ordersubmitfromshoppingcar_goods_old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        viewHolder.tv_ordersubmitfromshoppingcar_goods_current_price.setText(goodsList.get(position).getCurrent_price());

        //删除
        viewHolder.btn_ordersubmitfromshoppingcar_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("暂不支持！");
            }
        });

        //减法
        viewHolder.tv_ordersubmitfromshoppingcar_subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("1".equals(goodsList.get(position).getItems_num())) {
                    ToastUtils.showToast("至少买一件商品！");
                } else {
                    goodsList.get(position).setItems_num((Integer.parseInt(goodsList.get(position).getItems_num()) - 1) + "");
                    mChangeOrderAmountListener.orderAmountChanged(goodsList);
                }
            }
        });

        viewHolder.tv_ordersubmitfromshoppingcar_amount.setText(goodsList.get(position).getItems_num());


        //加法
        viewHolder.tv_ordersubmitfromshoppingcar_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(goodsList.get(position).getInventory()) <= Integer.parseInt(goodsList.get(position).getItems_num())) {
                    ToastUtils.showToast("商品最大库存为" + goodsList.get(position).getInventory() + "！");
                } else {

                    goodsList.get(position).setItems_num((1 + Integer.parseInt(goodsList.get(position).getItems_num())) + "");
                    mChangeOrderAmountListener.orderAmountChanged(goodsList);

                }
            }
        });


        viewHolder.tv_ordersubmitfromshoppingcar_totalmoney.setText("" + MathUtils.number2dot2(Double.parseDouble(goodsList.get(position).getItems_num()) * (Double.parseDouble(goodsList.get(position).getCurrent_price()))));

        viewHolder.tv_ordersubmitfromshoppingcar_amount_desc.setText("共" + goodsList.get(position).getItems_num() + "件商品，合计");




        return convertView;
    }







    class ViewHolder {

        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_goods_name)
        TextView tv_ordersubmitfromshoppingcar_goods_name;
        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_goods_desc)
        TextView tv_ordersubmitfromshoppingcar_goods_desc;
        @ViewInject(R.id.iv_ordersubmitfromshoppingcar_goods_pic)
        ImageView iv_ordersubmitfromshoppingcar_goods_pic;
        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_goods_old_price)
        TextView tv_ordersubmitfromshoppingcar_goods_old_price;
        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_goods_current_price)
        TextView tv_ordersubmitfromshoppingcar_goods_current_price;
        @ViewInject(R.id.btn_ordersubmitfromshoppingcar_delete)
        Button btn_ordersubmitfromshoppingcar_delete;


        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_subtraction)
        TextView tv_ordersubmitfromshoppingcar_subtraction;
        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_amount)
        TextView tv_ordersubmitfromshoppingcar_amount;
        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_add)
        TextView tv_ordersubmitfromshoppingcar_add;
        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_totalmoney)
        TextView tv_ordersubmitfromshoppingcar_totalmoney;
        @ViewInject(R.id.tv_ordersubmitfromshoppingcar_amount_desc)
        TextView tv_ordersubmitfromshoppingcar_amount_desc;



    }




}
