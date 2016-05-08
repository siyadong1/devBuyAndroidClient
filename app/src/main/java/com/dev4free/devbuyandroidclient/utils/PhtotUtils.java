package com.dev4free.devbuyandroidclient.utils;

/**
 * Created by syd on 2016/5/7.
 */


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

/**
 * 相片相关的工具类
 */
public class PhtotUtils  {




    /**
     * 调用系统相机拍摄照片
     * 务必在Activity中回调protected void onActivityResult(int requestCode, int resultCode, Intent data)方法
     * 处理返回存储在file中的图片
     * @return
     */
    public static void takePhoto(Activity mActivity,File file,int requestCode) {



        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

        } catch (Exception e) {
        }

        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        mActivity.startActivityForResult(intent, requestCode);
    }


    /**
     * 从相册中选择照片
     * 务必在Activity中回调protected void onActivityResult(int requestCode, int resultCode, Intent data)方法
     * 处理返回图片
     * @return
     */
    public static void pickPhoto(Activity mActivity,int requestCode) {

        Intent intentPickPicture = new Intent();
        intentPickPicture.setAction(Intent.ACTION_GET_CONTENT);
        intentPickPicture.setType("image/*");
        mActivity.startActivityForResult(intentPickPicture, requestCode);

    }


    /**
     * 获取存储在URi中的图片路径取
     * @param activity
     * @param data
     * @return
     */
    public static String handleUri(Activity activity,Intent data) {

        Uri selectedImage = data.getData();
        String[] filePathColumns={MediaStore.Images.Media.DATA};
        Cursor c = activity.getContentResolver().query(selectedImage, filePathColumns, null,null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        String picturePath= c.getString(columnIndex);
        c.close();
        return  picturePath;
    }









    /**
     * 按照给定的宽和高，进行压缩
     *
     * @param pathName     文件路径
     * @param targetWidth  压缩的宽度
     * @param targetHeight 压缩的高度
     * @return
     */
    public static Bitmap compressBySize(String pathName, int targetWidth,
                                 int targetHeight) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;// 不去真的解析图片，只是获取图片的头部信息，包含宽高等；
        Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
        // 得到图片的宽度、高度；
        float imgWidth = opts.outWidth;
        float imgHeight = opts.outHeight;
        Log.e("sydlog", "压缩前图片的宽度opts.outWidth=" + imgWidth);
        Log.e("sydlog", "压缩前图片的高度opts.outHeight=" + imgHeight);
        Log.e("sydlog", "压缩前图片的大小=" + imgHeight * imgWidth / 1024 / 1024 * 2 + "MB");
        // 分别计算图片宽度、高度与目标宽度、高度的比例；取大于等于该比例的最小整数；
        int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
        int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
        opts.inSampleSize = 1;
        if (widthRatio > 1 || widthRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        // 设置好缩放比例后，加载图片进内容；
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(pathName, opts);

        Log.e("sydlog", "opts.inSampleSize=" + opts.inSampleSize);
        Log.e("sydlog", "压缩后图片的宽度=" + bitmap.getWidth());
        Log.e("sydlog", "压缩后图片的高度=" + bitmap.getHeight());
        Log.e("sydlog", "压缩后图片的大小=" + bitmap.getWidth() * bitmap.getHeight() / 1024 * 2 + "KB");

        return bitmap;
    }



}
