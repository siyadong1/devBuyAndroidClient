package com.dev4free.devbuyandroidclient.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.dev4free.devbuyandroidclient.R;

/**
 * Created by syd on 2016/4/26.
 */
public class TitleBarView extends LinearLayout{



    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_titlebar,this);
        LinearLayout llBack = (LinearLayout) findViewById(R.id.ll_title_back);
        llBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
