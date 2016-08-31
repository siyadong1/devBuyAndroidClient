package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main1.CityActivity;
import com.dev4free.devbuyandroidclient.activity.main2.GoodsDetail;
import com.dev4free.devbuyandroidclient.activity.main2.GoodsList;
import com.dev4free.devbuyandroidclient.adapter.Main1BannerPagerAdapter;
import com.dev4free.devbuyandroidclient.adapter.Main1NavigatorAdapter;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.entity.BannerBean;
import com.dev4free.devbuyandroidclient.entity.NavigatarBean;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main1 extends BaseFragment implements ViewPager.OnPageChangeListener,AdapterView.OnItemClickListener{



    int currentItem = 0;

    ScheduledExecutorService mScheduledExecutorService = null;

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    List<BannerBean> bannersList = new ArrayList<BannerBean>();
    List<NavigatarBean> navigatorList = new ArrayList<NavigatarBean>();
    Main1BannerPagerAdapter bannerPagerAdapter = null;
    Main1NavigatorAdapter navigatorAdapter =null;



    @ViewInject(R.id.et_main1_search)
    EditText et_main1_search;

    @ViewInject(R.id.ll_main1_city)
    LinearLayout ll_main1_city;

    @ViewInject(R.id.vp_main1_banner)
    ViewPager vp_main1_banner;

    @ViewInject(R.id.gv_main1_navigation)
    GridView gv_main1_navigation;


    @ViewInject(R.id.tv_main1_city)
    TextView tv_main1_city;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main1,null);
        mContext = getActivity();
        x.view().inject(this,view);
        progressDialogUtils = new ProgressDialogUtils(mContext);


       getBannerData();
       getNavigatorData();


        vp_main1_banner.addOnPageChangeListener(this);
        gv_main1_navigation.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (TextUtils.isEmpty(SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.CITYNAME, "")) || SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.CITYNAME, "").equals("无")) {
            tv_main1_city.setText("请选择");
        } else {
            tv_main1_city.setText(SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.CITYNAME,""));
        }

    }



    @Event(R.id.ll_main1_city)
    private void goToCity(View view) {

        Intent intent = new Intent(mContext, CityActivity.class);
        startActivity(intent);

    }



    @Override
    public void onStop() {
        super.onStop();
        //停止图片切换
        if (mScheduledExecutorService != null) {
            mScheduledExecutorService.shutdown();
        }
    }

    @Override
    public void onStart() {

        super.onStart();
        //用一个定时器  来完成图片切换
        //Timer 与 ScheduledExecutorService 实现定时器的效果

        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //通过定时器 来完成 每2秒钟切换一个图片
        //经过指定的时间后，执行所指定的任务
        //scheduleAtFixedRate(command, initialDelay, period, unit)
        //command 所要执行的任务
        //initialDelay 第一次启动时 延迟启动时间
        //period  每间隔多次时间来重新启动任务
        //unit 时间单位
        mScheduledExecutorService.scheduleAtFixedRate(new ViewPagerTask(), 5, 5, TimeUnit.SECONDS);


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        currentItem = position;


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent = new Intent(mContext, GoodsList.class);
        intent.putExtra("category",navigatorList.get(position).getCategory());
        startActivity(intent);



    }


    /**
     * 初始化banner数据
     */
    public void getBannerData() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);

        HttpUtils.post(ConstantsUrl.bannerInitial, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {


                        bannersList = new Gson().fromJson(result.getJSONArray("content").toString(),new TypeToken<List<BannerBean>>(){}.getType());
                        if (bannersList != null && bannersList.size() > 0) {

                            //adapter
                            bannerPagerAdapter = new Main1BannerPagerAdapter(mContext,bannersList);
                            //bind
                            vp_main1_banner.setAdapter(bannerPagerAdapter);
                            vp_main1_banner.setCurrentItem(bannersList.size()*1000);
                            currentItem = bannersList.size()*1000;
                        }

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


    /**
     * 获取首页导航项目
     */
    public void getNavigatorData() {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);

        HttpUtils.post(ConstantsUrl.classificationInitial, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        navigatorList = new Gson().fromJson(result.getJSONArray("content").toString(),new TypeToken<List<NavigatarBean>>(){}.getType());
                        if (navigatorList != null && navigatorList.size() > 0) {
                            navigatorAdapter = new Main1NavigatorAdapter(mContext,navigatorList);
                            gv_main1_navigation.setAdapter(navigatorAdapter);
                        }

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


    //用来完成图片切换的任务
    private class ViewPagerTask implements Runnable{

        public void run() {
            //实现我们的操作
            //改变当前页面
            currentItem = currentItem + 1;
            //Handler来实现图片切换
            handler.sendEmptyMessage(1);
        }
    }
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            //设定viewPager当前页面

            if (msg.what == 1) {
                vp_main1_banner.setCurrentItem(currentItem);
            }

        }
    };



    @Event(value = {R.id.iv_main1_1_1_1,R.id.iv_main1_1_2_1,R.id.iv_main1_1_2_2,R.id.iv_main1_1_2_3,
            R.id.iv_main1_2_1_1,R.id.iv_main1_2_2_1,R.id.iv_main1_2_2_2,R.id.iv_main1_2_2_3,
            R.id.iv_main1_3_1_1,R.id.iv_main1_3_2_1,R.id.iv_main1_3_2_2,R.id.iv_main1_3_2_3
    })
    private void clickEvent(View view) {

        Intent intent = new Intent(mContext, GoodsDetail.class);
        startActivity(intent);

    }


}
