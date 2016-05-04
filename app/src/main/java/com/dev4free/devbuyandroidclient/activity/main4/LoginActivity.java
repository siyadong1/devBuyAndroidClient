package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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


    private Context mContext;



    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_login);
        x.view().inject(this);
        mContext = this;

    }


    /**
     * 注册
     */
 @Event(value = R.id.tv_login_register)
    private void registerEvent(){
     Intent intent = new Intent(mContext,RegisterActivity.class);
     startActivity(intent);

 }

    /**
     * 找回密码
     */
    @Event(value = R.id.tv_login_forgetpasswrod)
    private void forgetpasswrodEvent() {
        Intent intent = new Intent(mContext,ForgetPasswordActivity.class);
        startActivity(intent);
    }

}
