package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by syd on 2016/4/27.
 */
public class ForgetPasswordActivity extends BaseActivity {


    private Context mContext;

    @ViewInject(R.id.et_register_username)
    EditText et_register_username;


    @ViewInject(R.id.et_register_password)
    EditText et_register_password;


    @ViewInject(R.id.et_register_password_confirm)
    EditText et_register_password_confirm;



    @ViewInject(R.id.et_register_msgcode)
    EditText et_register_msgcode;


    @ViewInject(R.id.btn_register_msgcode)
    EditText btn_register_msgcode;



    @ViewInject(R.id.btn_register_confirm)
    EditText btn_register_confirm;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.activity_register);
        mContext = this;
        initTitle("注册");

    }
}
