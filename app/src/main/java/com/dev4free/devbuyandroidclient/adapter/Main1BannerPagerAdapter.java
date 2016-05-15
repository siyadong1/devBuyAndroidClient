package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev4free.devbuyandroidclient.R;

import java.util.List;

/**
 * Created by syd on 2016/5/13.
 */
public class Main1BannerPagerAdapter extends PagerAdapter {




    Context mContext;
    List<Drawable> bannerList;

    public Main1BannerPagerAdapter(Context mContext,List<Drawable> bannerList) {
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
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_fragment1_banner,null);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_fragment1_banner);
        imageView.setImageDrawable(bannerList.get(position%bannerList.size()));
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

      container.removeView((View)object);

    }
}
