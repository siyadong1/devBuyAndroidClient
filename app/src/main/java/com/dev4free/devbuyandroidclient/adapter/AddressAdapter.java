package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.AlertInterface;
import com.dev4free.devbuyandroidclient.Interface.DeleteAddressListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main4.AddressUpdateActivity;
import com.dev4free.devbuyandroidclient.entity.Address;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class AddressAdapter extends BaseAdapter {

    Context mContext;
    List<Address> addresses;
    DeleteAddressListener mDeleteAddressListener;

    public AddressAdapter(Context mContext,List<Address> addresses,DeleteAddressListener mDeleteAddressListener) {
        this.addresses = addresses;
        this.mContext = mContext;
        this.mDeleteAddressListener = mDeleteAddressListener;
    }

    @Override
    public int getCount() {
        return addresses.size();
    }

    @Override
    public Object getItem(int position) {
        return addresses.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_manageaddress_item,null);
            x.view().inject(viewHolder,convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_manageaddress_name.setText("收货人：" + addresses.get(position).getConsignee_name());
        viewHolder.tv_manageaddress_phone.setText(addresses.get(position).getPhone_number());
        viewHolder.tv_manageaddress_address.setText("收货地址：" + addresses.get(position).getProvince() + addresses.get(position).getCity() + addresses.get(position).getDetail_address());
        if ("0".equals(addresses.get(position).getDefault_address())) {
            viewHolder.tv_manageaddress_defaultaddress.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.address_default, 0, 0, 0);
        } else {
            viewHolder.tv_manageaddress_defaultaddress.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.address_no_defaultaddress, 0, 0, 0);
        }


        viewHolder.tv_manageaddress_editaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddressUpdateActivity.class);
                intent.putExtra("address_id",addresses.get(position).getAddress_id());
                intent.putExtra("consignee_name",addresses.get(position).getConsignee_name());
                intent.putExtra("phone_number",addresses.get(position).getPhone_number());
                intent.putExtra("province",addresses.get(position).getProvince());
                intent.putExtra("city",addresses.get(position).getCity());
                intent.putExtra("detail_address",addresses.get(position).getDetail_address());
                intent.putExtra("default_address",addresses.get(position).getDefault_address());
                mContext.startActivity(intent);
            }
        });

        viewHolder.tv_manageaddress_deleteaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialogUtils.showAlertDialog(mContext, "确定删除地址吗","取消","删除", new AlertInterface() {
                    @Override
                    public void confirm(AlertDialog alertDialog) {
                        mDeleteAddressListener.deleteAddress(addresses.get(position).getAddress_id());
                        alertDialog.dismiss();
                    }
                });
            }
        });



        return convertView;
    }


    class ViewHolder{

        @ViewInject(R.id.tv_ordersubmit_address_name)
        TextView tv_manageaddress_name;
        @ViewInject(R.id.tv_ordersubmit_address_phone)
        TextView tv_manageaddress_phone;
        @ViewInject(R.id.tv_ordersubmit_address_detail_address)
        TextView tv_manageaddress_address;
        @ViewInject(R.id.tv_manageaddress_defaultaddress)
        TextView tv_manageaddress_defaultaddress;
        @ViewInject(R.id.tv_manageaddress_editaddress)
        TextView tv_manageaddress_editaddress;
        @ViewInject(R.id.tv_manageaddress_deleteaddress)
        TextView tv_manageaddress_deleteaddress;

    }



}
