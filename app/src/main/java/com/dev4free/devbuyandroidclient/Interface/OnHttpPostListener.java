package com.dev4free.devbuyandroidclient.Interface;

import org.json.JSONObject;

/**
 * Created by syd on 2016/5/4.
 * 采用httpPost请求网络，返回请求的结果
 */
public interface OnHttpPostListener {

    void onSuccess(JSONObject result) ;
    void onError(Throwable ex, boolean isOnCallback);

}
