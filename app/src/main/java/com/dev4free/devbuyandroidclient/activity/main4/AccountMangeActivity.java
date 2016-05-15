package com.dev4free.devbuyandroidclient.activity.main4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.OnHttpPostListener;
import com.dev4free.devbuyandroidclient.R;
import com.dev4free.devbuyandroidclient.activity.BaseActivity;
import com.dev4free.devbuyandroidclient.constants.ConstantsHttp;
import com.dev4free.devbuyandroidclient.constants.ConstantsUrl;
import com.dev4free.devbuyandroidclient.constants.ConstantsUser;
import com.dev4free.devbuyandroidclient.utils.AlertDialogUtils;
import com.dev4free.devbuyandroidclient.utils.HttpUtils;
import com.dev4free.devbuyandroidclient.utils.LogUtil;
import com.dev4free.devbuyandroidclient.utils.PhtotUtils;
import com.dev4free.devbuyandroidclient.utils.ProgressDialogUtils;
import com.dev4free.devbuyandroidclient.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syd on 2016/5/6.
 */
public class AccountMangeActivity extends BaseActivity implements View.OnClickListener {

    private ProgressDialogUtils progressDialogUtils;
    private Context mContext;
    PopupWindow mPopupWindow;
    LinearLayout llTakePhoto ;
    LinearLayout llPickPhoto ;
    LinearLayout llCancelphoto ;
    RelativeLayout rl_accountmanage_avatar ;
    LinearLayout llMan ;
    LinearLayout llWoman ;
    LinearLayout llCancelGender ;
    RelativeLayout rl_accountmanage_gender ;
    File file = new File(Environment.getExternalStorageDirectory(), "temp_iden.png");




    @ViewInject(R.id.tv_accountmanage_gender)
    TextView tv_accountmanage_gender;
    @ViewInject(R.id.tv_accountmanage_nickname)
    TextView tv_accountmanage_nickname;
    @ViewInject(R.id.iv_accountmanage_photo)
    ImageView iv_accountmanage_photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountmanage);
        x.view().inject(this);
        mContext = this;
        progressDialogUtils = new ProgressDialogUtils(mContext);
        initTitle("账号管理");


    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {

        tv_accountmanage_nickname.setText(ConstantsUser.nickname);
        tv_accountmanage_gender.setText(ConstantsUser.gender);
    }


    /**
     * 处理点击事件
     * @param view
     */
  @Event(value = {R.id.ll_accountmanage_avatar,R.id.ll_accountmanage_nickname,R.id.ll_accountmanage_gender,R.id.ll_accountmanage_address})
    private void clickEvent(View view) {


      switch (view.getId()) {

          //修换头像
          case R.id.ll_accountmanage_avatar:


              popupWindowAvatar();


              break;

          //修改昵称
          case R.id.ll_accountmanage_nickname:

              Intent intent = new Intent(mContext,NickNameActivity.class);
              startActivity(intent);

              break;

          //修改性别
          case R.id.ll_accountmanage_gender:

              popupWindowGender();
              break;

          //修改地址
          case R.id.ll_accountmanage_address:
              intent = new Intent(mContext,AddressManageActivity.class);
              startActivity(intent);
              break;


      }

  }





    /**
     * 弹出头像的popupWindow
     */
    private void popupWindowAvatar() {

        mPopupWindow = new PopupWindow(this);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_accountmanage_avatar,null);
        llTakePhoto = (LinearLayout) view.findViewById(R.id.ll_accountmanage_takephoto);
        llTakePhoto.setOnClickListener(this);
        llPickPhoto = (LinearLayout) view.findViewById(R.id.ll_accountmanage_pickphoto);
        llPickPhoto.setOnClickListener(this);
        llCancelphoto = (LinearLayout) view.findViewById(R.id.ll_accountmanage_cancelphoto);
        llCancelphoto.setOnClickListener(this);
        rl_accountmanage_avatar = (RelativeLayout) view.findViewById(R.id.rl_accountmanage_avatar);
        rl_accountmanage_avatar.setOnClickListener(this);
        mPopupWindow.setContentView(view);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
//        mPopupWindow.setAnimationStyle(R.style.my_persionInfo_AnimBottom);
        mPopupWindow.showAtLocation(AccountMangeActivity.this
                .findViewById(R.id.ll_accountmanage), Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

/**
     * 弹出性别popupWindow
     */
    private void popupWindowGender() {

        mPopupWindow = new PopupWindow(this);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_accountmanage_gender,null);
        llMan = (LinearLayout) view.findViewById(R.id.ll_accountmanage_man);
        llMan.setOnClickListener(this);
        llWoman = (LinearLayout) view.findViewById(R.id.ll_accountmanage_woman);
        llWoman.setOnClickListener(this);
        llCancelGender = (LinearLayout) view.findViewById(R.id.ll_accountmanage_cancelgender);
        llCancelGender.setOnClickListener(this);
        rl_accountmanage_gender = (RelativeLayout) view.findViewById(R.id.rl_accountmanage_gender);
        rl_accountmanage_gender.setOnClickListener(this);
        mPopupWindow.setContentView(view);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.showAtLocation(AccountMangeActivity.this
                .findViewById(R.id.ll_accountmanage), Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            //拍照
            case R.id.ll_accountmanage_takephoto:
                mPopupWindow.dismiss();

                PhtotUtils.takePhoto(this,file,1);

                break;

            //从相册中选择
            case R.id.ll_accountmanage_pickphoto:
                mPopupWindow.dismiss();
                PhtotUtils.pickPhoto(this,2);
                break;
            //头像取消
            case R.id.ll_accountmanage_cancelphoto:
                mPopupWindow.dismiss();
                break;

            //头像点击屏幕其他区域取消
            case R.id.rl_accountmanage_avatar:
                mPopupWindow.dismiss();
                break;





            //男
            case R.id.ll_accountmanage_man:
                mPopupWindow.dismiss();
                modifyGender("男");
                break;
            //女
            case R.id.ll_accountmanage_woman:
                mPopupWindow.dismiss();
                modifyGender("女");
                break;
            //性别取消
            case R.id.ll_accountmanage_cancelgender:
                mPopupWindow.dismiss();
                break;
            //性别点击屏幕其他区域取消
            case R.id.rl_accountmanage_gender:
                mPopupWindow.dismiss();
                break;

        }

    }


    /**
     * 修改性别
     */
    private void modifyGender(final String gender) {


        progressDialogUtils.showProgress();

        Map<String,String> map = new HashMap<String,String >();
        String username = ConstantsUser.username;
        map.put("username",username);
        map.put("gender",gender);

        HttpUtils.post(ConstantsUrl.modifygender, map, new OnHttpPostListener() {
            @Override
            public void onSuccess(JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {



                        ConstantsUser.gender = gender;
                        tv_accountmanage_gender.setText(gender);

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
     * 处理接收到的图片
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {

            // 对拍照之后的图片进行压缩。
            final Bitmap bitmap = PhtotUtils.compressBySize(file.getPath(), 500, 500);
            LogUtil.e("takephoto="+ file.getPath());
            //显示图片
            iv_accountmanage_photo.setImageBitmap(bitmap);

            Bitmap b = PhtotUtils.compressImage(bitmap);

            ByteArrayOutputStream byos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG,100,byos);

            try {
                FileOutputStream f = new FileOutputStream(file);
                try {
                    f.write(byos.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            uploadFile();



        } else if (requestCode == 2 && resultCode == RESULT_OK) {

            String picturePath = PhtotUtils.handleUri(this,data);
            final Bitmap bitmap = PhtotUtils.compressBySize(picturePath, 500, 500);
            //显示图片
            iv_accountmanage_photo.setImageBitmap(bitmap);
            LogUtil.e("pickphoto="+ picturePath);

            Bitmap b = PhtotUtils.compressImage(bitmap);

            ByteArrayOutputStream byos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG,100,byos);

            try {
                FileOutputStream f = new FileOutputStream(file);
                try {
                    f.write(byos.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            uploadFile();





        } else {
            ToastUtils.showToast("拍照失败...");
        }

    }


    /**
     * 上传图片
     */
    private void uploadFile() {

        progressDialogUtils.showProgress("正在上传头像...");

        Map<String,String> map = new HashMap<String,String >();
        Map<String,File>   mapFile = new HashMap<String,File>();
        String username = ConstantsUser.username;

        map.put("username",username);
        mapFile.put("avatarpic",file);
        HttpUtils.uploadFile(ConstantsUrl.modifyavatar, map,mapFile, new OnHttpPostListener() {
            @Override
            public void onSuccess(final JSONObject result) {
                progressDialogUtils.dismissProgress();
                try {
                    if (result.getString(ConstantsHttp.CODE).equals(ConstantsHttp.CODENormal)) {

                        ToastUtils.showToast("头像修改成功！");
                        ConstantsUser.avatar = URLDecoder.decode(result.getString(ConstantsHttp.CONTENT),"UTF-8");
                        finish();

                    } else {
                        AlertDialogUtils.showAlertDialog(mContext,result.getString(ConstantsHttp.CONTENT));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialogUtils.showAlertDialog(mContext,getString(R.string.json_parse_error));
                } catch (UnsupportedEncodingException e) {
                    AlertDialogUtils.showAlertDialog(mContext,getString(R.string.json_url_parse_error));
                    e.printStackTrace();
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
