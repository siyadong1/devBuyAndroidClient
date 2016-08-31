package com.dev4free.devbuyandroidclient.Interface;

import com.dev4free.devbuyandroidclient.entity.ShoppingCarItems;

import java.util.List;

/**
 * Created by syd on 2016/8/28.
 */
public interface ChangeOrderAmountListener {

    void orderAmountChanged(List<ShoppingCarItems> goodsList);

}
