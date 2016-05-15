package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syd on 2016/5/6.
 */
public class NickNameActivity extends BaseActivity {



    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    @ViewInject(R.id.et_nickname_nickname)
    EditText et_nickname_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initView();
    }

    /**
     * 初始化view
     */
    private void initView() {

        et_nickname_nickname.setText(ConstantsUser.nickname);
    }


    /**
     * 点击事件绑定
     * @param view
     */
    @Event(R.id.btn_nickname_confirm)
    private void confirmEvent(View view) {

        if (checkClickName()) {

            modifyNickName();

        } else {
            ToastUtils.showToast(getString(R.string.nick_empty));
        }

    }

    /**
     * 修改昵称
     */
    private void modifyNickName() {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = ConstantsUser.username;
        String nickname = et_nickname_nickname.getText().toString().trim();

        map.put("username",username);
        map.put("nickname",nickname);

        HttpUtils.post(ConstantsUrl.modifynickname, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        ConstantsUser.nickname = et_nickname_nickname.getText().toString().trim();
                        finish();

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
     * 检查是否填写nickName
     */
    private boolean checkClickName() {

        return et_nickname_nickname.getText().toString().trim().length() > 0;

    }
}
