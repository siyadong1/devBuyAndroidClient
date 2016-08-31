package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main2.GoodsDetail;
import com.dev4free.devbuyandroidclient.entity.RecommandBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by syd on 2016/8/31.
 */
public class Main1RecommandAdapter extends BaseAdapter {


    Context mContext;
    List<List<RecommandBean>> recommandList;


    public Main1RecommandAdapter(Context mContext, List<List<RecommandBean>> recommandList) {
        this.mContext = mContext;
        this.recommandList = recommandList;
    }

    @Override
    public int getCount() {
        return recommandList.size();
    }

    @Override
    public Object getItem(int position) {
        return recommandList.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_fragment1_recommand, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tv_fragment1_recommand_title.setText(recommandList.get(position).get(0).getRc_category());
        Glide.with(mContext).load(recommandList.get(position).get(0).getRc_url()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_fragment1_recommand_pic1_1);
        Glide.with(mContext).load(recommandList.get(position).get(1).getRc_url()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_fragment1_recommand_pic2_1);
        Glide.with(mContext).load(recommandList.get(position).get(2).getRc_url()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_fragment1_recommand_pic2_2);
        Glide.with(mContext).load(recommandList.get(position).get(3).getRc_url()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_fragment1_recommand_pic2_3);


        viewHolder.iv_fragment1_recommand_pic1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetail.class);
                intent.putExtra("items_id",recommandList.get(position).get(0).getItems_id());
                mContext.startActivity(intent);
            }
        });

        viewHolder.iv_fragment1_recommand_pic2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetail.class);
                intent.putExtra("items_id",recommandList.get(position).get(1).getItems_id());
                mContext.startActivity(intent);
            }
        });


        viewHolder.iv_fragment1_recommand_pic2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetail.class);
                intent.putExtra("items_id",recommandList.get(position).get(2).getItems_id());
                mContext.startActivity(intent);
            }
        });



        viewHolder.iv_fragment1_recommand_pic2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetail.class);
                intent.putExtra("items_id",recommandList.get(position).get(3).getItems_id());
                mContext.startActivity(intent);
            }
        });

        return convertView ;
    }



    class ViewHolder {

        @ViewInject(R.id.tv_fragment1_recommand_title)
        TextView tv_fragment1_recommand_title;
        @ViewInject(R.id.iv_fragment1_recommand_pic1_1)
        ImageView iv_fragment1_recommand_pic1_1;
        @ViewInject(R.id.iv_fragment1_recommand_pic2_1)
        ImageView iv_fragment1_recommand_pic2_1;
        @ViewInject(R.id.iv_fragment1_recommand_pic2_2)
        ImageView iv_fragment1_recommand_pic2_2;
        @ViewInject(R.id.iv_fragment1_recommand_pic2_3)
        ImageView iv_fragment1_recommand_pic2_3;


    }



}
