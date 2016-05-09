package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.Address;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class AddressAdapter extends BaseAdapter {

    Context mContext;
    List<Address> addresses;


    public AddressAdapter(Context mContext,List<Address> addresses) {
        this.addresses = addresses;
        this.mContext = mContext;
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

        viewHolder.tv_manageaddress_name.setText("收货人：" + addresses.get(position).getName());
        viewHolder.tv_manageaddress_phone.setText(addresses.get(position).getPhone());
        viewHolder.tv_manageaddress_address.setText("收货地址：" + addresses.get(position).getAddress());
        if ("1".equals(addresses.get(position).getIsDefault())) {
            viewHolder.tv_manageaddress_defaultaddress.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.address_default, 0, 0, 0);
        } else {
            viewHolder.tv_manageaddress_defaultaddress.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.address_no_defaultaddress, 0, 0, 0);
        }

        viewHolder.tv_manageaddress_defaultaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("您点击了第个" + position + "收获地址！");
            }
        });

        return convertView;
    }


    class ViewHolder{

        @ViewInject(R.id.tv_manageaddress_name)
        TextView tv_manageaddress_name;
        @ViewInject(R.id.tv_manageaddress_phone)
        TextView tv_manageaddress_phone;
        @ViewInject(R.id.tv_manageaddress_address)
        TextView tv_manageaddress_address;
        @ViewInject(R.id.tv_manageaddress_defaultaddress)
        TextView tv_manageaddress_defaultaddress;
        @ViewInject(R.id.tv_manageaddress_editaddress)
        TextView tv_manageaddress_editaddress;
        @ViewInject(R.id.tv_manageaddress_deleteaddress)
        TextView tv_manageaddress_deleteaddress;

    }



}
