package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

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

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syd on 2016/5/9.
 */
public class AboutUsActivity extends BaseActivity {



    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;

    @ViewInject(R.id.tv_aboutus_title)
    TextView tv_aboutus_title;
    @ViewInject(R.id.tv_aboutus_content)
    TextView tv_aboutus_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        initTitle("关于我们");
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);
        getAboutUsInfo();
    }


    public void getAboutUsInfo() {

        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = SharedPreferenceUtils.getDefaultSharedPreferences().getString(ConstantsUser.USERNAME,"");

        map.put("username",username);

        HttpUtils.post(ConstantsUrl.findHomePagesAboutus, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {
                        JSONObject jsonObject = result.getJSONObject("content");
                        tv_aboutus_title.setText(jsonObject.optString("info_name"));
                        tv_aboutus_content.setText(jsonObject.optString("info_content"));

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
