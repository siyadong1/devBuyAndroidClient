package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.adapter.OrderPagerAdapter;
import com.dev4free.devbuyandroidclient.fragment.Fragment_order1;
import com.dev4free.devbuyandroidclient.utils.MeasureUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syd on 2016/5/9.
 */
public class OrderActivity extends FragmentActivity {

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    OrderPagerAdapter ordrePagerAdapter;
    List<Fragment> fragmentsList = new ArrayList<Fragment>();

    int currentIndex = -1;

    @ViewInject(R.id.vp_order)
    ViewPager vp_order;

    @ViewInject(R.id.iv_order_line)
    ImageView iv_order_line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        TextView tv_title_content = (TextView)findViewById(R.id.tv_title_content);
        tv_title_content.setText("我的订单");


        //data
        getFragments();
        //adapter
        ordrePagerAdapter = new OrderPagerAdapter(getSupportFragmentManager(),mContext,fragmentsList);
        //bind
        vp_order.setAdapter(ordrePagerAdapter);
        //指示器
        transIndicator(-1,0);
    }


    /**
     * 页卡滑动时，下面的横线也滑动的效果
     */



    private void  transIndicator(float fromIndex,float toIndex) {

        int bmpW = BitmapFactory.decodeResource(getResources(), R.mipmap.order_line).getWidth();// 获取图片宽度
        int offset = (MeasureUtils.getDisplayMetrics().widthPixels / 4 - bmpW) / 2;// 计算偏移量

        if (fromIndex == -1) {
            Animation animation = new TranslateAnimation(0, offset, 0, 0);
            animation.setFillAfter(true);
            animation.setDuration(300);
            iv_order_line.startAnimation(animation);
            currentIndex = 0;
        } else {
            Animation animation = new TranslateAnimation(fromIndex*MeasureUtils.getDisplayMetrics().widthPixels / 4 + offset, toIndex*MeasureUtils.getDisplayMetrics().widthPixels /4 + offset, 0, 0);
            animation.setFillAfter(true);
            animation.setDuration(300);
            iv_order_line.startAnimation(animation);

        }



    }


    /**
     * 根据各个Fragment来填充ViewPager
     */
    public void getFragments() {

        fragmentsList.add(new Fragment_order1());
        fragmentsList.add(new Fragment_order1());
        fragmentsList.add(new Fragment_order1());
        fragmentsList.add(new Fragment_order1());

    }




    @Event(value = {R.id.tv_order_title1,R.id.tv_order_title2,R.id.tv_order_title3,R.id.tv_order_title4})
    private void clickEvent(View view) {

        switch (view.getId()) {

            //全部
            case R.id.tv_order_title1:

                if (currentIndex != 0) {
                    changeFragment(0);
                    currentIndex = 0;
                }

                break;
            //代收款
            case R.id.tv_order_title2:

                if (currentIndex != 1) {
                    changeFragment(1);
                    currentIndex = 1;
                }

                break;
            //代发货
            case R.id.tv_order_title3:

                if (currentIndex != 2) {
                    changeFragment(2);
                    currentIndex = 2;
                }

                break;
            //待收货
            case R.id.tv_order_title4:

                if (currentIndex != 3) {
                    changeFragment(3);
                    currentIndex = 3;
                }

                break;

        }

    }

    /**
     * 切换fragment
     */
    private void changeFragment(int nextIndex) {

        vp_order.setCurrentItem(nextIndex);
        transIndicator(currentIndex,nextIndex);

    }
}
