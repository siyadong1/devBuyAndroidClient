package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by syd on 2016/5/9.
 */
public class FeedBackActivity extends BaseActivity {

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    @ViewInject(R.id.et_feedback_content)
    EditText et_feedback_content;
    @ViewInject(R.id.btn_feedback_submit)
    EditText btn_feedback_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);
        initTitle("意见反馈");
    }


    /**
     * 处理点击事件
     * @param view
     */
    @Event(R.id.btn_feedback_submit)
    private void clickEvent(View view) {

        finish();


    }
}
