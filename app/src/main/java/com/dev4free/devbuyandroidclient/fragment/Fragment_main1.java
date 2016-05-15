package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main1.CityActivity;
import com.dev4free.devbuyandroidclient.adapter.Main1BannerPagerAdapter;
import com.dev4free.devbuyandroidclient.adapter.Main1NavigatorAdapter;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main1 extends BaseFragment implements ViewPager.OnPageChangeListener{



    int currentItem = 0;

    ScheduledExecutorService mScheduledExecutorService = null;

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    List<Drawable> bannersList = new ArrayList<Drawable>();
    List<Drawable> navigatorList = new ArrayList<Drawable>();
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



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main1,null);
        mContext = getActivity();
        x.view().inject(this,view);
        progressDialogUtils = new ProgressDialogUtils(mContext);
        //data
        initBanners();
        initNavigator();
        //adapter
        bannerPagerAdapter = new Main1BannerPagerAdapter(mContext,bannersList);
        navigatorAdapter = new Main1NavigatorAdapter(mContext,navigatorList);
        //bind
        vp_main1_banner.setAdapter(bannerPagerAdapter);
        vp_main1_banner.addOnPageChangeListener(this);
        vp_main1_banner.setCurrentItem(bannersList.size()*1000);
        currentItem = bannersList.size()*1000;
        gv_main1_navigation.setAdapter(navigatorAdapter);

        return view;
    }


    /**
     * 获取导航栏的资源
     */
    private void initNavigator() {

        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_1_1));
        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_1_2));
        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_1_3));
        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_1_4));

        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_2_1));
        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_2_2));
        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_2_3));
        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_2_4));


        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_3_1));
        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_3_2));
        navigatorList.add(getResources().getDrawable(R.mipmap.home_grid_3_3));

    }


    /**
     * 获取Banner的资源
     */
    private void initBanners() {

        bannersList.add(getResources().getDrawable(R.mipmap.banner1));
        bannersList.add(getResources().getDrawable(R.mipmap.banner2));
        bannersList.add(getResources().getDrawable(R.mipmap.banner3));

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


}
