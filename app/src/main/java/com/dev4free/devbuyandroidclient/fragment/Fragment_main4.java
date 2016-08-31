package com.dev4free.devbuyandroidclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.AlertInterface;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.main4.AboutUsActivity;
import com.dev4free.devbuyandroidclient.activity.main4.AccountMangeActivity;
import com.dev4free.devbuyandroidclient.activity.main4.FeedBackActivity;
import com.dev4free.devbuyandroidclient.activity.main4.LoginActivity;
import com.dev4free.devbuyandroidclient.activity.main4.ModifyPasswordActivity;
import com.dev4free.devbuyandroidclient.activity.main4.OrderActivity;
import com.dev4free.devbuyandroidclient.activity.main4.WalletRechargeActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.MathUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syd on 2016/4/26.
 */

public class Fragment_main4 extends BaseFragment{

    private Context mContext;

    ImageOptions imageOptions;



    private ProgressDialogUtils progressDialogUtils;



    @ViewInject(R.id.tv_main4_username)
    TextView tv_main4_username;
    @ViewInject(R.id.tv_main4_wallet_money)
    TextView tv_main4_wallet_money;
    @ViewInject(R.id.iv_main4_avatar)
    ImageView iv_main4_avatar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();

        View view = inflater.inflate(R.layout.fragment_main4,null);
        x.view().inject(this,view);
        progressDialogUtils = new ProgressDialogUtils(mContext);
        ((TextView)view.findViewById(R.id.tv_title_content)).setText("我的");
        view.findViewById(R.id.ll_title_back).setVisibility(View.GONE);


        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(50), DensityUtil.dip2px(50))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                .setRadius(DensityUtil.dip2px(50))
                .setLoadingDrawableId(R.mipmap.my_avator)
                .setFailureDrawableId(R.mipmap.my_avator)
                .build();


        if (SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"") != null) {

        }

        return view;

    }




    /**
     * 处理点击事件
     * @param view
     */

    @Event(value = {R.id.ll_main4_exit,R.id.ll_main4_modify_password,R.id.ll_main4_feedback,
                    R.id.ll_main4_aboutus,R.id.ll_main4_custom_srevice,R.id.ll_main4_order,R.id.ll_main4_order_daifukuan,
                    R.id.ll_main4_order_daifahuo,R.id.ll_main4_order_daishouhuo,R.id.ll_main4_avatar,R.id.ll_main4_wallet})
    private void clickEvent(View view) {
        Intent intent;
        switch (view.getId()) {


            //代付款
            case R.id.ll_main4_order_daifukuan:

                intent = new Intent(mContext,OrderActivity.class);
                startActivity(intent);
                break;


            //代发货
            case R.id.ll_main4_order_daifahuo:

                intent = new Intent(mContext,OrderActivity.class);
                startActivity(intent);
                break;


            //代收获
            case R.id.ll_main4_order_daishouhuo:

                intent = new Intent(mContext,OrderActivity.class);
                startActivity(intent);
                break;



            //修改密码
            case R.id.ll_main4_order:

                intent = new Intent(mContext, OrderActivity.class);
                startActivity(intent);

                break;


            //进入钱包
            case R.id.ll_main4_wallet:

                intent = new Intent(mContext, WalletRechargeActivity.class);
                startActivity(intent);

                break;



            //客服热线
            case R.id.ll_main4_custom_srevice:



                AlertDialogUtils.showAlertDialog(mContext, "您将拨打110？", "取消", "拨打", new AlertInterface() {
                    @Override
                    public void confirm(AlertDialog alertDialog) {


                        //意图：想干什么事
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        //url:统一资源定位符
                        //uri:统一资源标示符（更广）
                        intent.setData(Uri.parse("tel:" + "15228935891"));
                        //开启系统拨号器
                        startActivity(intent);

                    }
                });



                break;


            //修改密码
            case R.id.ll_main4_modify_password:

                intent = new Intent(mContext, ModifyPasswordActivity.class);
                startActivity(intent);

                break;

            //账户管理
            case R.id.ll_main4_avatar:

                intent = new Intent(mContext, AccountMangeActivity.class);
                startActivity(intent);

                break;

            //意见反馈
            case R.id.ll_main4_feedback:

                intent = new Intent(mContext, FeedBackActivity.class);
                startActivity(intent);

                break;

            //关于我们
            case R.id.ll_main4_aboutus:

                intent = new Intent(mContext, AboutUsActivity.class);
                startActivity(intent);

                break;


            //安全退出
            case R.id.ll_main4_exit:
                intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                SharedPreferenceUtils.getDefaultSharedPreferences().edit().putString("login","no").commit();
                break;


        }


    }

    @Override
    public void onResume() {
        super.onResume();

        x.image().bind(iv_main4_avatar, ConstantsUser.avatar,imageOptions);
        getWalletAmount();
    }


    /**
     * 获取钱包余额
     */
    public void getWalletAmount() {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);

        HttpUtils.post(ConstantsUrl.userWalletQuery, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        tv_main4_wallet_money.setText(MathUtils.number2dot2(Double.parseDouble(result.optString("content"))) + "元");

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
}
