package com.dev4free.devbuyandroidclient.utils;

import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by syd on 2016/5/4.
 */
public class HttpUtils {


    public static void post(String url, Map<String,String> map, final OnHttpPostListener onHttpPostListener) {

//        params.addBodyParameter("username",username);
//        params.addBodyParameter("password",password);

        // 加到url里的参数, http://xxxx/s?wd=xUtils
        // 添加到请求body体的参数, 只有POST, PUT, PATCH, DELETE请求支持.
        // params.addBodyParameter("wd", "xUtils");

        // 使用multipart表单上传文件
//        params.setMultipart(true);
//        params.addBodyParameter(
//                "file",
//                new File("/sdcard/test.jpg"),
//                null); // 如果文件没有扩展名, 最好设置contentType参数.
//        try {
//            params.addBodyParameter(
//                    "file2",
//                    new FileInputStream(new File("/sdcard/test2.jpg")),
//                    "image/jpeg",
//                    // 测试中文文件名
//                    "你+& \" 好.jpg"); // InputStream参数获取不到文件名, 最好设置, 除非服务端不关心这个参数.
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        }
        RequestParams params = new RequestParams(url);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.addBodyParameter(entry.getKey(),entry.getValue());
        }

        LogUtil.e("httpPostSendData=" + map);
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                onHttpPostListener.onSuccess(result);
                LogUtil.e("httpPostReceiveData=" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                onHttpPostListener.onError(ex,isOnCallback);
                LogUtil.e("httpPostReceiveError=" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
//                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {
            }
        });


    }


}
