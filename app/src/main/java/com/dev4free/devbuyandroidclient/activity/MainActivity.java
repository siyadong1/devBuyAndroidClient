package com.dev4free.devbuyandroidclient.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main4.LoginActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.fragment.Fragment_main1;
import com.dev4free.devbuyandroidclient.fragment.Fragment_main2;
import com.dev4free.devbuyandroidclient.fragment.Fragment_main3;
import com.dev4free.devbuyandroidclient.fragment.Fragment_main4;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;

import cn.smssdk.SMSSDK;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    android.app.FragmentManager manager;
    FragmentTransaction transaction;
    RadioButton rb_main1;
    RadioGroup rg_main;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        SMSSDK.initSDK(this, "122be24f9ce3b", "ef2ee7086fdc48602a34478acfc3fb7a");
        initViews();

        rb_main1.setChecked(true);
        rg_main.setOnCheckedChangeListener(this);
        manager = getFragmentManager();
        changeFragment(new Fragment_main1());
    }

    private void initViews() {
        rb_main1 = (RadioButton) findViewById(R.id.rb_main1);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        switch (checkedId) {
            //首页
            case R.id.rb_main1:

                changeFragment(new Fragment_main1());
                break;
            //积分商城
            case R.id.rb_main2:
                changeFragment(new Fragment_main2());
                break;
            //订单
            case R.id.rb_main3:
                changeFragment(new Fragment_main3());

                break;
            //个人中心
            case R.id.rb_main4:

            //没有登录则需要先登录
                if (SharedPreferenceUtils.getDefaultSharedPreferences().getString("login","no").equals("yes")) {
                    changeFragment(new Fragment_main4());
                    ConstantsUser.username = SharedPreferenceUtils.getDefaultSharedPreferences().getString("username","");
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }



                break;
        }

    }



    private void changeFragment(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, fragment);
        transaction.commit();

    }

}
