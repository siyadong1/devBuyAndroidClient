package com.dev4free.devbuyandroidclient.activity.main1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.adapter.CityAdapter;
import com.dev4free.devbuyandroidclient.entity.City;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/4/26.
 */
public class CityActivity extends BaseActivity{



    List<City> cities = new ArrayList<City>();
    CityAdapter mCityAdatpter;

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    @ViewInject(R.id.lv_city)
    ListView lv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initTitle("所选城市");
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        //data
        getCities();
        //adapter
        mCityAdatpter = new CityAdapter(this,cities);
        //bind
        lv_city.setAdapter(mCityAdatpter);

    }

    /**
     * 获取城市列表
     */
    private void getCities() {

        for (int i = 0; i <30 ; i++) {
            City mCity = new City("A","测试城市"+ i);
            cities.add(mCity);
        }



    }


    @Event(value = R.id.lv_city,type = AdapterView.OnItemClickListener.class)
    private void ItemClickEvent(AdapterView<?> parent, View view, int position, long id) {

        ToastUtils.showToast("点击了" + cities.get(position).getName());

    }


}
