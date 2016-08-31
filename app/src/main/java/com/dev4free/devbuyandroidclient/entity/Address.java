package com.dev4free.devbuyandroidclient.entity;

/**
 * Created by syd on 2016/5/9.
 * 收货地址
 *
 */
public class Address {


    /**
     * default_address : 0
     * detail_address : 高新西区西源大道2006号
     * phone_number : 15228935891
     * consignee_name : 东哥
     * address_id : 66450b62636942e9b8281765aa674f26
     * province : 四川
     * user_id : e69d0e305728412d9e29d724b1f5b069
     * city : 成都
     */

    private String default_address;
    private String detail_address;
    private String phone_number;
    private String consignee_name;
    private String address_id;
    private String province;
    private String user_id;
    private String city;

    public String getDefault_address() {
        return default_address;
    }

    public void setDefault_address(String default_address) {
        this.default_address = default_address;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getConsignee_name() {
        return consignee_name;
    }

    public void setConsignee_name(String consignee_name) {
        this.consignee_name = consignee_name;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
