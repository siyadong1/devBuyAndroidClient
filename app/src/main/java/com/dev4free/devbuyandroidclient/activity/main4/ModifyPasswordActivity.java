package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dev4free.devbuyandroidclient.Interface.AlertInterface;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
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
 * Created by syd on 2016/5/6.
 */
public class ModifyPasswordActivity extends BaseActivity{

    @ViewInject(R.id.et_modifypassword_oldpassword)
    EditText et_modifypassword_oldpassword;
    @ViewInject(R.id.et_modifypassword_newpassword)
    EditText et_modifypassword_newpassword;
    @ViewInject(R.id.et_modifypassword_confirmpassword)
    EditText et_modifypassword_confirmpassword;
    @ViewInject(R.id.btn_modifypassword_confirm)
    EditText btn_modifypassword_confirm;

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypassword);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);
    }




    /**
     * 检验旧密码是否大于登录6位
     * @return
     */
    private boolean checkOldPassword() {
        return (!TextUtils.isEmpty(et_modifypassword_oldpassword.getText().toString())&&
                et_modifypassword_oldpassword.getText().toString().replaceAll(" ","").length() >= 6
        );
    }


    /**
     * 检验新密码是否为空
     * @return
     */
    private boolean checkNewPassword() {

        return (!TextUtils.isEmpty(et_modifypassword_newpassword.getText().toString())&&
                et_modifypassword_newpassword.getText().toString().replaceAll(" ","").length() >= 6
        );
    }

    /**
     * 检验确认密码是否为空
     * @return
     */
    private boolean checkConfirmPassword() {
        if (!TextUtils.isEmpty(et_modifypassword_confirmpassword.getText().toString()) ) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 检验新密码和确认密码是否一致
     * @return
     */
    private boolean checkPasswordIsSame() {
        if (et_modifypassword_confirmpassword.getText().toString().equals(et_modifypassword_newpassword.getText().toString()) ) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 修改密码
     * @param view
     */
    @Event(R.id.btn_modifypassword_confirm)
    private void confirmEvent(View view) {
        if (!checkOldPassword()) {
            ToastUtils.showToast(getString(R.string.modifypassword_old_empty));
            return;
        }
        if (!checkNewPassword()) {
            ToastUtils.showToast(getString(R.string.modifypassword_new_empty));
            return;
        }
        if (!checkConfirmPassword()) {
            ToastUtils.showToast(getString(R.string.modifypassword_confirm_empty));
            return;
        }
        if (!checkPasswordIsSame()) {
            ToastUtils.showToast(getString(R.string.modifypassword_password_different));
            return;
        }


        modifyPassword();

    }

    /**
     * 修改密码
     */
    private void modifyPassword() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username;
        if (!TextUtils.isEmpty(ConstantsUser.username)) {
            username = ConstantsUser.username;
        } else {
            username = SharedPreferenceUtils.getDefaultSharedPreferences().getString("username","");
        }
        String password = et_modifypassword_oldpassword.getText().toString();
        String newpassword = et_modifypassword_newpassword.getText().toString();
        String confirmpassword = et_modifypassword_confirmpassword.getText().toString();

        map.put("username",username);
        map.put("password",password);
        map.put("newpassword",newpassword);
        map.put("confirmpassword",confirmpassword);

        HttpUtils.post(ConstantsUrl.modifypassword, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                            AlertDialogUtils.showAlertDialog(mContext, "修改密码成功！", new AlertInterface() {
                                @Override
                                public void confirm(AlertDialog alertDialog) {

                                    finish();

                                }
                            });
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
