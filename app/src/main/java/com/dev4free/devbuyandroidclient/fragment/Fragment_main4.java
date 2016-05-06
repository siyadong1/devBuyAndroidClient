package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main4.AccountMangeActivity;
import com.dev4free.devbuyandroidclient.activity.main4.LoginActivity;
import com.dev4free.devbuyandroidclient.activity.main4.ModifyPasswordActivity;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main4 extends BaseFragment{

    private Context mContext;


    @ViewInject(R.id.ll_main4_modify_password)
    LinearLayout ll_main4_modify_password;
    @ViewInject(R.id.ll_main4_exit)
    LinearLayout ll_main4_exit;
    @ViewInject(R.id.tv_main4_username)
    TextView tv_main4_username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();

        View view = inflater.inflate(R.layout.fragment_main4,null);
        x.view().inject(this,view);
        ((TextView)view.findViewById(R.id.tv_title_content)).setText("我的");
        view.findViewById(R.id.ll_title_back).setVisibility(View.GONE);


        return view;

    }




    @Event(value = {R.id.ll_main4_exit,R.id.ll_main4_modify_password,R.id.tv_main4_username})
    private void clickEvent(View view) {
        Intent intent;
        switch (view.getId()) {

            //退出登录
            case R.id.ll_main4_exit:
                intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                SharedPreferenceUtils.getDefaultSharedPreferences().edit().putString("login","no").commit();
                break;

            //修改密码
            case R.id.ll_main4_modify_password:

                intent = new Intent(mContext, ModifyPasswordActivity.class);
                startActivity(intent);

                break;

            //账户管理
            case R.id.tv_main4_username:

                intent = new Intent(mContext, AccountMangeActivity.class);
                startActivity(intent);

                break;

        }


    }

}
