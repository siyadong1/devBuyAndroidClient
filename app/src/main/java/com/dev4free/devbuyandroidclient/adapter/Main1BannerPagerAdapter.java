package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main2.GoodsDetail;
import com.dev4free.devbuyandroidclient.entity.BannerBean;

import java.util.List;

/**
 * Created by syd on 2016/5/13.
 */
public class Main1BannerPagerAdapter extends PagerAdapter {




    Context mContext;
    List<BannerBean> bannerList;

    public Main1BannerPagerAdapter(Context mContext,List<BannerBean> bannerList) {
        this.mContext = mContext;
        this.bannerList = bannerList;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_fragment1_banner,null);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_fragment1_banner);
        Glide.with(mContext).load(bannerList.get(position%bannerList.size()).getBn_url()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(imageView);
        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetail.class);
                intent.putExtra("items_id",bannerList.get(position%bannerList.size()).getItems_id());
                mContext.startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

      container.removeView((View)object);

    }
}
