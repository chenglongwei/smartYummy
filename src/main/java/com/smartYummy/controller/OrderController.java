package com.smartYummy.controller;

import com.smartYummy.model.Order;
import com.smartYummy.model.OrderItem;
import com.smartYummy.service.ItemService;
import com.smartYummy.service.OrderService;
import com.smartYummy.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addItem(@RequestParam("item_id") long item_id, @RequestParam("quantity") int quantity) {
        OrderItem orderItem = getOrderItem(item_id);
        orderItem.setQuantity(quantity);
        shoppingCartService.addItem(orderItem);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    void updateItem(@RequestParam("item_id") long item_id, @RequestParam("quantity") int quantity) {
        OrderItem orderItem = getOrderItem(item_id);
        orderItem.setQuantity(quantity);
        shoppingCartService.updateItem(orderItem);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    void removeItem(@RequestParam("item_id") long item_id) {
        OrderItem orderItem = getOrderItem(item_id);
        shoppingCartService.removeItem(orderItem);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<OrderItem> getItems() {
        return shoppingCartService.getOrderItems();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    void save() {
        Order order = new Order();
        List<OrderItem> itemList = shoppingCartService.getOrderItems();
        order.setOrderItems(itemList);
        orderService.saveOrder(order);
    }

    private OrderItem getOrderItem(long item_id) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(itemService.findByID(item_id));
        return orderItem;
    }
}
