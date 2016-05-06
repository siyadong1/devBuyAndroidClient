package com.dev4free.devbuyandroidclient.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev4free.devbuyandroidclient.Interface.AlertInterface;
import com.dev4free.devbuyandroidclient.R;

/**
 * Created by syd on 2015/12/19.
 */
public class AlertDialogUtils {








    /**
     *只含有一个button的AlertDialog，点击确定后，默认取消
     * @param context
     * @param alertMessage
     */

    public static  void  showAlertDialog(Context context,String alertMessage) {


        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
        dialog.getWindow().setContentView(R.layout.alertdialog_type_one);
        ((TextView) dialog.getWindow().findViewById(R.id.tv_dialog_type_one_message)).setText(alertMessage);
        ((Button) dialog.getWindow().findViewById(R.id.btn_dialog_type_one_confirm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }


    /**
     *只含有一个button的AlertDialog，点击确定后，执行回调函数的事件
     * @param context
     * @param alertMessage
     * @param alertInterface
     */

    public static  void  showAlertDialog(Context context, String alertMessage, final AlertInterface alertInterface) {

        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(R.layout.alertdialog_type_one);
        ((TextView) dialog.getWindow().findViewById(R.id.tv_dialog_type_one_message)).setText(alertMessage);
        ((Button) dialog.getWindow().findViewById(R.id.btn_dialog_type_one_confirm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertInterface.confirm(dialog);
            }
        });

    }


    /**
     *返回有两个按钮的dialog
     * @param context
     * @param alertMessage
     * @param btnCancel
     * @param btnConfirm
     * @param confirmAlertInterface
     */

    public static  void  showAlertDialog(Context context,String alertMessage,String btnCancel,String btnConfirm, final AlertInterface confirmAlertInterface) {



        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.getWindow().setContentView(R.layout.alertdialog_type_two);
        ((TextView)dialog.getWindow().findViewById(R.id.tv_dialog_type_two_message)).setText(alertMessage);
        ((Button)dialog.getWindow().findViewById(R.id.btn_dialog_type_two_cancel)).setText(btnCancel);
        ((Button)dialog.getWindow().findViewById(R.id.btn_dialog_type_two_confirm)).setText(btnConfirm);
        ((Button)dialog.getWindow().findViewById(R.id.btn_dialog_type_two_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        ((Button)dialog.getWindow().findViewById(R.id.btn_dialog_type_two_confirm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               confirmAlertInterface.confirm(dialog);
            }
        });





    }



}
