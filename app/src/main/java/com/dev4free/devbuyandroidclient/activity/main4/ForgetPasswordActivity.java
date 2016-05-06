package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dev4free.devbuyandroidclient.Interface.AlertInterface;
import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
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

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by syd on 2016/4/27.
 */
public class ForgetPasswordActivity extends BaseActivity {




    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    EventHandler eh;

    private long totalTime = 122000;
    private long intervalTime = 1000;
    MyCountDownTimer countDownTimer;

    String phone;
    String msgCode;

    @ViewInject(R.id.et_forgetpassword_username)
    EditText et_forgetpassword_username;
 
    @ViewInject(R.id.et_forgetpassword_newpassword)
    EditText et_forgetpassword_newpassword;


    @ViewInject(R.id.et_forgetpassword_confirm_password)
    EditText et_forgetpassword_confirmpassword;



    @ViewInject(R.id.et_forgetpassword_msgcode)
    EditText et_forgetpassword_msgcode;


    @ViewInject(R.id.btn_forgetpassword_msgcode)
    Button btn_forgetpassword_msgcode;



    @ViewInject(R.id.btn_forgetpassword_confirm)
    EditText btn_forgetpassword_confirm;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgetpassword);
        initTitle("忘记密码");
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);

        initEventHandler();
    }





    /**
     * 初始化第三方短信接口
     */
    private void initEventHandler() {

        eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功   {phone=15228935891, country=86}
                        phone = (String) ((HashMap<String,Object>)data).get("phone");
                        msgCode = et_forgetpassword_msgcode.getText().toString();
                        Message message = Message.obtain();
                        message.what = 2;
                        registerHandle.sendMessage(message);

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        Message message = Message.obtain();
                        message.what = 1;
                        registerHandle.sendMessage(message);

                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表


                    }
                }else{
                    Message message = Message.obtain();
                    message.what = 3;
                    try {
                        message.obj = (new JSONObject(((Throwable)data).getMessage())).getString("detail");
                    } catch (JSONException e) {
                        ToastUtils.showToast("JSON解析异常！");
                        e.printStackTrace();
                    }
                    registerHandle.sendMessage(message);
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调


    }





    /**
     * 获取短信验证码
     * @param view
     */
    @Event(value = R.id.btn_forgetpassword_msgcode)
    private void getMsgCodeEvent(View view) {


        if (checkUserName()) {
            phone = "";
            msgCode = "";
            String phone = et_forgetpassword_username.getText().toString();
            countDownTimer = new MyCountDownTimer(totalTime,intervalTime);
            countDownTimer.start();
            SMSSDK.getVerificationCode("86", phone);

        } else {
            ToastUtils.showToast(getResources().getString(R.string.register_number_error));
        }

    }





    /**
     * 处理短信验证码的回调
     */
    private Handler registerHandle = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                //获取短信验证码成功
                case 1:

                    ToastUtils.showToast(getString(R.string.register_obtain_msg_success));

                    break;
                //短信校验通过
                case 2:

                    ToastUtils.showToast(getString(R.string.register_verify_msg_success));

                    submit();


                    break;
                //短信校验抛出异常
                case 3:
                    ToastUtils.showToast(msg.obj.toString());
                    countDownTimer.cancel();
                    btn_forgetpassword_msgcode.setEnabled(true);
                    btn_forgetpassword_msgcode.setText("重新获取");
                    break;
            }

        }
    };



    @Event(R.id.btn_forgetpassword_confirm)
    private void ConfirmEvent(View view) {



        if (!checkUserName()) {
            ToastUtils.showToast(getString(R.string.forgetpassword_number_error));
            return;
        }


        if (!checkMsgCode()) {
            ToastUtils.showToast(getString(R.string.forgetpassword_msgcode_error));
            return;
        }


        if (!checkNewPassword()) {
            ToastUtils.showToast(getString(R.string.forgetpassword_password_error));
            return;
        }


        if (!checkConfirmPassword()) {
            ToastUtils.showToast(getString(R.string.forgetpassword_password_confirm_error));
            return;
        }

        if (!checkPasswordIsSame()) {
            ToastUtils.showToast(getString(R.string.forgetpassword_password_different));
            return;
         }


        if (TextUtils.isEmpty(phone)) {
            verifyMsgCode();
        } else {
            submit();
        }


    }

    /**
     * 校验短信验证码是否正确
     */
    private void verifyMsgCode() {

        String phone = et_forgetpassword_username.getText().toString();
        String code = et_forgetpassword_msgcode.getText().toString();
        SMSSDK.submitVerificationCode("86", phone, code);

    }

    /**
     * 提交修改密码请求
     */
    private void submit() {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = et_forgetpassword_username.getText().toString();
        String newpassword = et_forgetpassword_newpassword.getText().toString();
        String confirmpassword = et_forgetpassword_confirmpassword.getText().toString();

        map.put("username",username);
        map.put("newpassword",newpassword);
        map.put("confirmpassword",confirmpassword);

        HttpUtils.post(ConstantsUrl.forgetpassword, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        AlertDialogUtils.showAlertDialog(mContext, "重置密码成功！", new AlertInterface() {
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


    /**
     * 检验用户名是否为空
     * @return
     */
    private boolean checkUserName() {
        if (!TextUtils.isEmpty(et_forgetpassword_username.getText().toString()) ) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 检验短信验证码是否为空
     * @return
     */
    private boolean checkMsgCode() {
        if (!TextUtils.isEmpty(et_forgetpassword_msgcode.getText().toString()) ) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 检验新密码是否大于等于6位
     * @return
     */
    private boolean checkNewPassword() {
        return (!TextUtils.isEmpty(et_forgetpassword_newpassword.getText().toString())&&
                et_forgetpassword_newpassword.getText().toString().replaceAll(" ","").length() >= 6
        );
    }

    /**
     * 检验确认密码是否大于等于6位
     * @return
     */
    private boolean checkConfirmPassword() {
        return (!TextUtils.isEmpty(et_forgetpassword_confirmpassword.getText().toString())&&
                et_forgetpassword_confirmpassword.getText().toString().replaceAll(" ","").length() >= 6
        );
    }

    /**
     * 检验新密码和确认密码是否一致
     * @return
     */
    private boolean checkPasswordIsSame() {
        return et_forgetpassword_confirmpassword.getText().toString().equals(et_forgetpassword_newpassword.getText().toString());

    }




    /**
     * 获取短信验证码的时候，显示倒计时
     */
    class MyCountDownTimer extends CountDownTimer {

        /**
         *
         * @param millisInFuture
         *            倒计时总时间
         * @param countDownInterval
         *            每隔多久触发onTick方法
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {
            btn_forgetpassword_msgcode.setText("重新获取");
            btn_forgetpassword_msgcode.setEnabled(true);
        }

        @Override
        public void onTick(long arg0) { // arg0 表示距离本级倒计时结束还有多长时间

            btn_forgetpassword_msgcode.setText("重新获取" + "(" + ((arg0 / 1000) -1) + ")" );
            btn_forgetpassword_msgcode.setEnabled(false);

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
