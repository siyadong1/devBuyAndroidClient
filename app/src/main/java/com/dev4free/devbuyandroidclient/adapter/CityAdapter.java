package com.dev4free.devbuyandroidclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.entity.City;
import com.dev4free.devbuyandroidclient.view.PinnedHeaderListView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;
import java.util.Locale;

/**
 * Created by syd on 2016/5/8.
 */
public class CityAdapter extends BaseAdapter implements SectionIndexer,PinnedHeaderListView.PinnedHeaderAdapter,AbsListView.OnScrollListener {

    Context mContext;
    List<City> listData;
    private int mLocationPosition = -1;

    public CityAdapter(Context mContext, List<City> listData) {

        this.mContext = mContext;
        this.listData = listData;

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

        ViewHolder mViewHolder = null;
        if (convertView == null) {

            mViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_city_item, null);
            x.view().inject(mViewHolder, convertView);
            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }


        int section = getSectionForPosition(position);

        if (position == getPositionForSection(section)) {
            mViewHolder.tv_city_symbol.setText(listData.get(position).getSymbol());
            mViewHolder.tv_city_symbol.setVisibility(View.VISIBLE);
        } else {
            mViewHolder.tv_city_symbol.setVisibility(View.GONE);
        }

        mViewHolder.tv_city_name.setText(listData.get(position).getName());



        return convertView;
    }


    private class ViewHolder {
        @ViewInject(R.id.tv_city_symbol)
        TextView tv_city_symbol;
        @ViewInject(R.id.tv_city_name)
        TextView tv_city_name;

    }




    /**
     * ****************************************SectionIndexer接口的处理******************************************
     * @return
     */
    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    /**
     * 返回当前首字母出现的第一个位置
     */
    @Override
    public int getPositionForSection(int sectionIndex) {

        for (int i = 0; i < listData.size(); i++)
        {
            char firstChar = listData.get(i).getSymbol().toUpperCase(Locale.CHINA).charAt(0);
            if (firstChar == sectionIndex)
            {
                return i;
            }
        }
        return -1;

    }

    /**
     * 返回当前位置的首字母
     */
    @Override
    public int getSectionForPosition(int position) {
        return listData.get(position).getSymbol().toUpperCase(Locale.CHINA).charAt(0);
    }


    /**
     *****************************************PinnedHeaderAdapter接口的处理方法******************************************
     * @param position
     * @return
     */
    @Override
    public int getPinnedHeaderState(int position) {
        int realPosition = position;
        if (realPosition < 0 || (mLocationPosition != -1 && mLocationPosition == realPosition))
        {
            return PINNED_HEADER_GONE;
        }
        mLocationPosition = -1;
        if (listData.size() > 1)
        {
            int nextSection = getSectionForPosition(realPosition + 1);
            int nextSectionPosition = getPositionForSection(nextSection);
            if (nextSectionPosition != -1 && realPosition == nextSectionPosition - 1)
            {
                return PINNED_HEADER_PUSHED_UP;
            }
        }
        return PINNED_HEADER_VISIBLE;
    }

    @Override
    public void configurePinnedHeader(View header, int position, int alpha) {

        ((TextView) header.findViewById(R.id.tv_city_pinnedheader)).setText(listData.get(position).getSymbol().toUpperCase(Locale.CHINA).charAt(0) + "");


    }


    /**
     *****************************************PinnedHeaderAdapter接口的处理方法******************************************
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view instanceof PinnedHeaderListView)
        {
            ((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
        }
    }

}

