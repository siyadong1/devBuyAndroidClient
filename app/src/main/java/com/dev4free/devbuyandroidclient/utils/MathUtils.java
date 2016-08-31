package com.dev4free.devbuyandroidclient.utils;

/**
 * Created by syd on 2016/8/29.
 */
public class MathUtils {


    /**
     * 保留小数点后两位小数
     * @param number
     * @return
     */
    public static double number2dot2(double number) {

        String doubleNumber = number + "";
        int positon = doubleNumber.lastIndexOf(".");
        if (positon == -1) {
            return number;
        }
        int length = doubleNumber.length();
        if (length - positon < 4) {
            return number;
        } else {
            return Double.parseDouble(doubleNumber.substring(0,positon + 3));
        }

    }


}
