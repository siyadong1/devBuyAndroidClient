package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.activity.MainActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.SharedPreferenceUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syd on 2016/4/27.
 */
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.et_login_username)
    EditText et_login_username;

    @ViewInject(R.id.et_login_password)
    EditText et_login_password;

    @ViewInject(R.id.tv_login_register)
    TextView tv_login_register;

    @ViewInject(R.id.tv_login_forgetpasswrod)
    TextView tv_login_forgetpasswrod;


    @ViewInject(R.id.btn_login_login)
    Button btn_login_login;

    @ViewInject(R.id.tv_login_version)
    TextView tv_login_version;

    private CheckBox ck_login;
    private Context mContext;
    private ProgressDialogUtils progressDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        x.view().inject(this);
        initTitle("登录");
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initView();


    }

    /**
     * 初始化控件
     */
    private void initView() {
        ck_login = (CheckBox) findViewById(R.id.ck_login);
        et_login_username.setText(SharedPreferenceUtils.getDefaultSharedPreferences().getString("username",""));
        et_login_password.setText(SharedPreferenceUtils.getDefaultSharedPreferences().getString("password",""));

        try {
            tv_login_version.setText("V " + getPackageManager().getPackageInfo(getPackageName(),0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_login_version.setText("版本未知");
        }
    }

    /**
     * 注册
     */
 @Event(value = R.id.tv_login_register)
    private void registerEvent(View view){
     Intent intent = new Intent(mContext,RegisterActivity.class);
     startActivity(intent);

 }


    /**
     * 找回密码
     */
    @Event(value = R.id.tv_login_forgetpasswrod)
    private void forgetpasswrodEvent(View view) {
        Intent intent = new Intent(mContext,ForgetPasswordActivity.class);
        startActivity(intent);
    }


    /**
     * 登录
     */
    @Event(value = R.id.btn_login_login)
    private void loginEvent(View view) {


        if (!checkUserName()) {
            ToastUtils.showToast(getString(R.string.login_username_error));
            return;
        }
        if (!checkUserPassword()) {
            ToastUtils.showToast(getString(R.string.login_password_error));
            return;
        }

        SharedPreferenceUtils.getDefaultSharedPreferences().edit().putString("username",et_login_username.getText().toString()).commit();
        if (ck_login.isChecked()) {
            SharedPreferenceUtils.getDefaultSharedPreferences().edit().putString("password", et_login_password.getText().toString()).commit();
        } else {
            SharedPreferenceUtils.getDefaultSharedPreferences().edit().putString("password", "").commit();

        }
        login();

    }

    /**
     * 用户登录
     */
    private void login() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String>();
        map.put("username",et_login_username.getText().toString());
        map.put("password",et_login_password.getText().toString());

        HttpUtils.post(ConstantsUrl.login, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result)  {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {


                        Intent intent = new Intent(mContext, MainActivity.class);
                        startActivity(intent);
                        SharedPreferenceUtils.getDefaultSharedPreferences().edit().putString("login","yes").commit();
                        SharedPreferenceUtils.getDefaultSharedPreferences().edit().putString(ConstantsUser.USERNAME,et_login_username.getText().toString()).commit();

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
     * 检验用户名是否正确
     * @return
     */
    private boolean checkUserName() {
        if (!TextUtils.isEmpty(et_login_username.getText().toString()) && et_login_username.getText().toString().length() == 11) {
            return true;
        } else {
            return false;
        }
    }



    /**
     * 检验密码是否为空
     * @return
     */
    private boolean checkUserPassword() {
        if (!TextUtils.isEmpty(et_login_password.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }


}
