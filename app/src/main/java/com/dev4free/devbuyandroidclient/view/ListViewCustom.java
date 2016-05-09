package com.dev4free.devbuyandroidclient.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by syd on 2016/5/9.
 */
public class ListViewCustom extends BaseListView {


    public ListViewCustom(Context context) {
        super(context);
    }

    public ListViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
