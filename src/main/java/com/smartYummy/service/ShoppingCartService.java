package com.smartYummy.service;

import com.smartYummy.model.OrderItem;
import com.smartYummy.model.ShoppingCart;

import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
public interface ShoppingCartService {
    ShoppingCart addItem(OrderItem orderItem);
    ShoppingCart removeItem(OrderItem orderItem);
    ShoppingCart updateItem(OrderItem orderItem);
    List<OrderItem> getOrderItems();
}
