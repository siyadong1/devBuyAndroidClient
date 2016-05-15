package com.dev4free.devbuyandroidclient.constants;

/**
 * Created by syd on 2016/5/4.
 */
public class ConstantsUrl {

//    public static final String HOST = "http://192.168.100.84:8080/";
    public static final String HOST = "http://www.dev4free.com/";
    public static final String PATH = "devbuy/java/";
    public static final String SUFFIX = ".action";


    /**
     * 商户注册
     */
    public static String register = HOST + PATH + "register" + SUFFIX;


    /**
     * 商户登录
     */
    public static String login = HOST + PATH + "login" + SUFFIX;



    /**
     * 商户修改密码
     */
    public static String modifypassword = HOST + PATH + "modifypassword" + SUFFIX;




    /**
     * 商户忘记密码
     */
    public static String forgetpassword = HOST + PATH + "forgetpassword" + SUFFIX;





    /**
     * 商户修改昵称
     */
    public static String modifynickname = HOST + PATH + "modifynickname" + SUFFIX;




    /**
     * 商户修改性别
     */
    public static String modifygender = HOST + PATH + "modifygender" + SUFFIX;





    /**
     * 修改用户头像
     */
    public static String modifyavatar = HOST + PATH + "modifyavatar" + SUFFIX;


    /**
     * 查询用户信息
     */
    public static String findUserByName = HOST + PATH + "findUserByName" + SUFFIX;

}
