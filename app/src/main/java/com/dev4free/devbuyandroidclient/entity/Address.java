package com.dev4free.devbuyandroidclient.entity;

/**
 * Created by syd on 2016/5/9.
 * 收货地址
 *
 */
public class Address {



    private String name;
    private String phone;
    private String address;
    private String isDefault;

    public  Address(){

    }

    public Address(String name, String phone,String address, String isDefault) {
        this.address = address;
        this.isDefault = isDefault;
        this.name = name;
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
