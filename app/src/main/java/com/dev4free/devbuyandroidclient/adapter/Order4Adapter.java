package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev4free.devbuyandroidclient.Interface.OrderCancelListener;
import com.dev4free.devbuyandroidclient.Interface.OrderPaymentListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.constants.ConstantsOrderState;
import com.dev4free.devbuyandroidclient.entity.OrderGoods;
import com.dev4free.devbuyandroidclient.utils.MathUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class Order4Adapter extends BaseAdapter{


    Context mContext;
    List<OrderGoods> goodsList;
    OrderCancelListener mOrderCancelListener;
    OrderPaymentListener mOrderPaymentListener;


    public Order4Adapter(Context mContext, List<OrderGoods> goodsList, OrderCancelListener mOrderCancelListener, OrderPaymentListener mOrderPaymentListener) {
        this.goodsList = goodsList;
        this.mContext = mContext;
        this.mOrderCancelListener = mOrderCancelListener;
        this.mOrderPaymentListener = mOrderPaymentListener;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_order4_item, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tv_order4_goods_name.setText(goodsList.get(position).getItemsname());
        Glide.with(mContext).load(goodsList.get(position).getImage()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_order4_goods_pic);
        viewHolder.tv_order4_goods_desc.setText(goodsList.get(position).getDescription());
        viewHolder.tv_order4_goods_current_price.setText(goodsList.get(position).getCurrent_price());
        viewHolder.tv_order4_goods_old_price.setText(goodsList.get(position).getPrice());
        viewHolder.tv_order4_goods_old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        viewHolder.tv_order4_amount_desc.setText("共" + goodsList.get(position).getItems_num() + "件商品，合计");
        viewHolder.tv_order4_totalmoney.setText("" + MathUtils.number2dot2(Double.parseDouble(goodsList.get(position).getItems_num()) * (Double.parseDouble(goodsList.get(position).getCurrent_price()))));
        if (ConstantsOrderState.orderState0.equals(goodsList.get(position).getState())) {
            viewHolder.tv_order4_goods_state.setText("等待付款");
            viewHolder.tv_order4_goods_state.setTextColor(mContext.getResources().getColor(R.color.red));
            viewHolder.rl_order4_cancel_and_payment.setVisibility(View.VISIBLE);
            viewHolder.btn_order4_confirm_payment.setVisibility(View.VISIBLE);
            viewHolder.btn_order4_cancel_payment.setVisibility(View.VISIBLE);
        } else if (ConstantsOrderState.orderState1.equals(goodsList.get(position).getState())) {
            viewHolder.tv_order4_goods_state.setText("等待发货");
            viewHolder.tv_order4_goods_state.setTextColor(mContext.getResources().getColor(R.color.yellow));
            viewHolder.rl_order4_cancel_and_payment.setVisibility(View.VISIBLE);
            viewHolder.btn_order4_confirm_payment.setVisibility(View.GONE);
            viewHolder.btn_order4_cancel_payment.setVisibility(View.VISIBLE);
        } else if (ConstantsOrderState.orderState2.equals(goodsList.get(position).getState())) {
            viewHolder.tv_order4_goods_state.setText("等待收货");
            viewHolder.tv_order4_goods_state.setTextColor(mContext.getResources().getColor(R.color.yellow));
            viewHolder.rl_order4_cancel_and_payment.setVisibility(View.VISIBLE);
            viewHolder.btn_order4_confirm_payment.setVisibility(View.GONE);
            viewHolder.btn_order4_cancel_payment.setVisibility(View.VISIBLE);
        }else if (ConstantsOrderState.orderState3.equals(goodsList.get(position).getState())) {
            viewHolder.tv_order4_goods_state.setText("已完成");
            viewHolder.tv_order4_goods_state.setTextColor(mContext.getResources().getColor(R.color.green));
            viewHolder.rl_order4_cancel_and_payment.setVisibility(View.GONE);
        }else if (ConstantsOrderState.orderState4.equals(goodsList.get(position).getState())) {
            viewHolder.tv_order4_goods_state.setText("已取消");
            viewHolder.tv_order4_goods_state.setTextColor(mContext.getResources().getColor(R.color.gray));
            viewHolder.rl_order4_cancel_and_payment.setVisibility(View.GONE);
        }

        viewHolder.btn_order4_cancel_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrderCancelListener.orderCancel(goodsList.get(position).getOrders_id());
            }
        });

        viewHolder.btn_order4_confirm_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrderPaymentListener.orderPayment(goodsList.get(position).getOrders_id(),goodsList.get(position).getSum());
            }
        });



        return convertView;
    }



    class ViewHolder {

        @ViewInject(R.id.tv_order4_goods_name)
        TextView tv_order4_goods_name;
        @ViewInject(R.id.iv_order4_goods_pic)
        ImageView iv_order4_goods_pic;
        @ViewInject(R.id.tv_order4_goods_desc)
        TextView tv_order4_goods_desc;
        @ViewInject(R.id.tv_order4_goods_current_price)
        TextView tv_order4_goods_current_price;
        @ViewInject(R.id.tv_order4_goods_old_price)
        TextView tv_order4_goods_old_price;
        @ViewInject(R.id.btn_order4_cancel_payment)
        Button btn_order4_cancel_payment;
        @ViewInject(R.id.btn_order4_confirm_payment)
        Button btn_order4_confirm_payment;
        @ViewInject(R.id.tv_order4_amount_desc)
        TextView tv_order4_amount_desc;
        @ViewInject(R.id.tv_order4_totalmoney)
        TextView tv_order4_totalmoney;
        @ViewInject(R.id.tv_order4_goods_state)
        TextView tv_order4_goods_state;
        @ViewInject(R.id.rl_order4_cancel_and_payment)
        RelativeLayout rl_order4_cancel_and_payment;

    }

}
