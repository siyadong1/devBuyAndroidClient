package com.dev4free.devbuyandroidclient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by syd on 2016/5/13.
 */
public class GridViewCustom extends GridView {
    public GridViewCustom(Context context) {
        super(context);
    }

    public GridViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandMeasureSpec);



    }
}
