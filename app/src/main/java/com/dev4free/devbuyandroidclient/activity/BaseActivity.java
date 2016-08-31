package com.dev4free.devbuyandroidclient.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.OnClickTitleBack;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.utils.ActivityUtils;
import com.dev4free.devbuyandroidclient.utils.LogUtil;
import com.umeng.message.PushAgent;

/**
 * Created by syd on 2016/4/26.
 */
public class BaseActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LogUtil.e(this.getClass().getName());
        ActivityUtils.addActivity(this);
        PushAgent.getInstance(this).onAppStart();
    }





    public void initTitle(String titleContent) {
        TextView tv_title_content = (TextView)findViewById(R.id.tv_title_content);
        tv_title_content.setText(titleContent);
    }

    public void initBackDesc(String titleBackDesc) {
        TextView tv_title_back_desc = (TextView)findViewById(R.id.tv_title_back_desc);
        tv_title_back_desc.setText(titleBackDesc);
    }


    public void initRight(String titleRight) {
        TextView tv_title_right = (TextView)findViewById(R.id.tv_title_right);
        tv_title_right.setText(titleRight);
    }


    public void hideTitleBack(){
        LinearLayout ll_title_back = (LinearLayout)findViewById(R.id.ll_title_back);
        ll_title_back.setVisibility(View.GONE);
    }

    public void hideTitleBackArror(){
        TextView tv_title_back_arror = (TextView)findViewById(R.id.tv_title_back_arror);
        tv_title_back_arror.setVisibility(View.GONE);
    }

    public void clickTitleBack(final OnClickTitleBack onClickTitleBack){
        LinearLayout ll_title_back = (LinearLayout)findViewById(R.id.ll_title_back);
        ll_title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTitleBack.onClick(v);
            }
        });
    }





    //  监听onTouchEvent事件，关闭软键盘。
    //  getWindow().getDecorView()的意思是获取window的最前面的view。软键盘是phonewindow的根view
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //关闭软键盘
        InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        methodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        return super.onTouchEvent(event);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }
}
