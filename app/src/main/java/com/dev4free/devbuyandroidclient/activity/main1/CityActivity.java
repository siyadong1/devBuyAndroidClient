package com.dev4free.devbuyandroidclient.activity.main1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.adapter.CityAdapter;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.entity.City;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;
import com.dev4free.devbuyandroidclient.view.BladeView;
import com.dev4free.devbuyandroidclient.view.PinnedHeaderListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syd on 2016/4/26.
 */
public class CityActivity extends BaseActivity{



    List<City> listData = new ArrayList<City>();
    CityAdapter mCityAdatpter;

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    @ViewInject(R.id.phlv_city)
    PinnedHeaderListView phlv_city;
    @ViewInject(R.id.bv_city)
    BladeView bv_city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initTitle("所在城市");


        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        //data
        getCities();


    }

    /**
     * 获取城市列表
     */
    private void getCities() {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);

        HttpUtils.post(ConstantsUrl.modifycityname, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {


                        listData = new Gson().fromJson(result.getJSONArray("content").toString(),
                                new TypeToken<List<City>>(){}.getType());







                        if (listData != null && listData.size() == 0) {
                            listData.add(new City("无","城市列表"));
                        }
                        //adapter
                        mCityAdatpter = new CityAdapter(CityActivity.this,listData);
                        //bind
                        phlv_city.setAdapter(mCityAdatpter);
                        phlv_city.setOnScrollListener(mCityAdatpter);
                        phlv_city.setPinnedHeaderView(getLayoutInflater().inflate(R.layout.view_city_pinnedheader,phlv_city,false));


                        bv_city.setOnItemClickListener(new BladeView.OnItemClickListener() {
                            @Override
                            public void onItemClick(String s) {

                                for (int i = 0; i <listData.size() ; i++) {

                                    if (listData.get(i).getInitial().equals(s)) {
                                        phlv_city.setSelection(i);
                                        break;
                                    }
                                }
                            }
                        });





                    } else {
                        AlertDialogUtils.showAlertDialog(mContext,result.getString(ConstantsHttp.CONTENT));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialogUtils.showAlertDialog(mContext,getString(R.string.json_parse_error));
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                progressDialogUtils.dismissProgress();
                AlertDialogUtils.showAlertDialog(mContext,getString(R.string.server_error));
            }
        });

    }


    @Event(value = R.id.phlv_city,type = AdapterView.OnItemClickListener.class)
    private void ItemClickEvent(AdapterView<?> parent, View view, int position, long id) {

        SharedPreferenceUtils.getDefaultSharedPreferences().edit().putString(ConstantsUser.CITYNAME,listData.get(position).getCityName()).commit();
        finish();


    }


}
