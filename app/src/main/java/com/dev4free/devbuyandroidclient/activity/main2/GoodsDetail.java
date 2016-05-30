package com.dev4free.devbuyandroidclient.activity.main2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.activity.main3.OrderSubmitActivity;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by syd on 2016/5/28.
 */
public class GoodsDetail extends BaseActivity {


    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;


    @ViewInject(R.id.tv_goodsdetail_price_old)
    TextView tv_goodsdetail_price_old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);
        initTitle("商品详情");

        tv_goodsdetail_price_old.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);


    }


    @Event(value = {R.id.btn_goodsdeail_gotoshoppingpcar,R.id.btn_goodsdeail_gotobuy})
    private void clickEvent(View view) {
        Intent intent = null;
        switch (view.getId()) {


            case R.id.btn_goodsdeail_gotoshoppingpcar:

                ToastUtils.showToast("已加入购物车！");

                break;


            case R.id.btn_goodsdeail_gotobuy:
                intent = new Intent(mContext, OrderSubmitActivity.class);
                startActivity(intent);

                break;



        }



    }

}
