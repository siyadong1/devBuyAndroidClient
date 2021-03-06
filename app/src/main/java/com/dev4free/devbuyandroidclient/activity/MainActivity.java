package com.dev4free.devbuyandroidclient.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dev4free.devbuyandroidclient.Interface.AlertInterface;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main4.LoginActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.fragment.Fragment_main1;
import com.dev4free.devbuyandroidclient.fragment.Fragment_main2;
import com.dev4free.devbuyandroidclient.fragment.Fragment_main3;
import com.dev4free.devbuyandroidclient.fragment.Fragment_main4;
import com.dev4free.devbuyandroidclient.utils.ActivityUtils;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;
import com.pgyersdk.update.PgyUpdateManager;
import com.umeng.message.PushAgent;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.SMSSDK;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    android.app.FragmentManager manager;
    FragmentTransaction transaction;
    @ViewInject(R.id.rg_main)
    RadioGroup rg_main;
    @ViewInject(R.id.rb_main1)
    RadioButton rb_main1;

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        x.view().inject(this);
        progressDialogUtils = new ProgressDialogUtils(mContext);
        rb_main1.setChecked(true);
        rg_main.setOnCheckedChangeListener(this);
        manager = getFragmentManager();
        changeFragment(new Fragment_main1());
        //ShareSdk短信调用
        SMSSDK.initSDK(this, "122be24f9ce3b", "ef2ee7086fdc48602a34478acfc3fb7a");
        //蒲公英自动升级
        PgyUpdateManager.register(this);
        //u盟推送
        PushAgent mPushAgent = PushAgent.getInstance(mContext);
        mPushAgent.enable();
        getUserInfo();

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

                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }



                break;
        }

    }



    public void changeFragment(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main, fragment);
        transaction.commit();

    }


    /**
     * 获取用户信息
     * @return
     */
    public void getUserInfo() {

//        XmlPullParser parser = getResources().getXml(R.xml.progressWheel);
//        AttributeSet attributes = Xml.asAttributeSet(parser);


//        XmlPullParser parser =  getResources().getXml(R.xml.view_progresswheel);
//        Xml.asAttributeSet(parser); // 得到AttributeSet
//        ProgressWheel progressWheel = new ProgressWheel(mContext, Xml.asAttributeSet(parser));
//        progressWheel.spin();
//        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);

        HttpUtils.post(ConstantsUrl.findUserByName, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
//                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {


                           JSONObject userInfo = result.getJSONObject(ConstantsHttp.CONTENT);

                        ConstantsUser.avatar = userInfo.getString("avatar");
                        ConstantsUser.gender = userInfo.getString("gender");
                        ConstantsUser.nickname = userInfo.getString("nickname");

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
//                progressDialogUtils.dismissProgress();
                AlertDialogUtils.showAlertDialog(mContext,getString(R.string.server_error));
            }
        });

    }


    @Override
    public void onBackPressed() {


        AlertDialogUtils.showAlertDialog(mContext, "您确定要残忍的退出App吗！", "取消", "确定", new AlertInterface() {
            @Override
            public void confirm(AlertDialog alertDialog) {
                ActivityUtils.removeAllActivity();
            }
        });

    }
}
