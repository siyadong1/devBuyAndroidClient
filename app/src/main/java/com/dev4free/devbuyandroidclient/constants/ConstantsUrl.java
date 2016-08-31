package com.dev4free.devbuyandroidclient.constants;

/**
 * Created by syd on 2016/5/4.
 */
public class ConstantsUrl {

    //syd
//    public static final String HOST = "http://192.168.100.133:8080/";
    //lzw
    public static final String HOST = "http://192.168.1.101:8080/";
    //normal
//    public static final String HOST = "http://www.dev4free.com/";
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
     * app首页分类导航模块初始化
     */
    public static String classificationInitial = HOST + PATH + "classificationInitial" + SUFFIX;





    /**
     * 用户钱包充值
     */
    public static String userWalletRecharge = HOST + PATH + "userWalletRecharge" + SUFFIX;






    /**
     * 	分类导航按类别查询商品详情
     */
    public static String queryItemsDetailByCategory = HOST + PATH + "queryItemsDetailByCategory" + SUFFIX;





    /**
     * APP首页初始化-banner模块
     */
    public static String bannerInitial = HOST + PATH + "bannerInitial" + SUFFIX;




    /**
     * APP首页初始化数据
     */
    public static String initialPages = HOST + PATH + "initialPages" + SUFFIX;




    /**
     * 取消用户订单
     */
    public static String cancelOrdersByOrdersId = HOST + PATH + "cancelOrdersByOrdersId" + SUFFIX;





    /**
     * 查看用户订单
     */
    public static String findOrdersByUserName = HOST + PATH + "findOrdersByUserName" + SUFFIX;




    /**
     * 订单付款
     */
    public static String payForOrdersByOrdersId = HOST + PATH + "payForOrdersByOrdersId" + SUFFIX;





    /**
     * 用户钱包查询
     */
    public static String userWalletQuery = HOST + PATH + "userWalletQuery" + SUFFIX;


    /**
     * 提交订单
     */
    public static String submitOrders = HOST + PATH + "submitOrders" + SUFFIX;



    /**
     * 关于我们
     */
    public static String findHomePagesAboutus = HOST + PATH + "findHomePagesAboutus" + SUFFIX;




    /**
     * 更新我的首页意见反馈信息
     */
    public static String updateHomePagesFeedback = HOST + PATH + "updateHomePagesFeedback" + SUFFIX;




    /**
     * 删除购物车商品信息
     */
    public static String deleteItemsFromShoppingCart = HOST + PATH + "deleteItemsFromShoppingCart" + SUFFIX;





    /**
     * 商品加入购物车
     */
    public static String appendItemsToShoppingCart = HOST + PATH + "appendItemsToShoppingCart" + SUFFIX;




    /**
     * 获取购物车商品信息
     */
    public static String findShoppingCartDetailsByUserName = HOST + PATH + "findShoppingCartDetailsByUserName" + SUFFIX;




    /**
     * 查询商品详情
     */
    public static String findItemsByItemsId = HOST + PATH + "findItemsByItemsId" + SUFFIX;




    /**
     * 删除收货地址
     */
    public static String deleteShippingAddress = HOST + PATH + "deleteShippingAddress" + SUFFIX;




    /**
     * 插入用户收货地址
     */
    public static String insertShippingAddress = HOST + PATH + "insertShippingAddress" + SUFFIX;




    /**
     * 更新用户收货地址
     */
    public static String updateShippingAddress = HOST + PATH + "updateShippingAddress" + SUFFIX;




    /**
     * 根据用户名称查询用户收货地址
     */
    public static String findAddressByUserName = HOST + PATH + "findAddressByUserName" + SUFFIX;






    /**
     * 请求城市列表
     */
    public static String modifycityname = HOST + PATH + "modifycityname" + SUFFIX;




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
