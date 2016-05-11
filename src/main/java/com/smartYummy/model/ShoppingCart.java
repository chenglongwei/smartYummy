package com.smartYummy.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
public class ShoppingCart implements Serializable {
    private List<OrderItem> orderItems;

    public ShoppingCart() {
        orderItems = new LinkedList<OrderItem>();
    }

    public void addItem(OrderItem orderItem) {
        OrderItem foundItem = findOrderItem(orderItem.getId());
        if (foundItem == null) {
            orderItems.add(orderItem);
        } else {
            // found item, only increase quantity.
            foundItem.setQuantity(foundItem.getQuantity() + orderItem.getQuantity());
        }
    }

    public void removeItem(OrderItem orderItem) {
        OrderItem foundItem = findOrderItem(orderItem.getId());
        orderItems.remove(foundItem);
    }

    // only update order item quantity
    public void updateItem(OrderItem orderItem) {
        OrderItem foundItem = findOrderItem(orderItem.getId());
        if (foundItem != null) {
            foundItem.setQuantity(orderItem.getQuantity());
        }
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    private OrderItem findOrderItem(long id) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId() == id) {
                return orderItem;
            }
        }
        return null;
    }
}
