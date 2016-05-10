package com.smartYummy.controller;

import com.smartYummy.model.CurrentUser;
import com.smartYummy.model.Order;
import com.smartYummy.model.OrderItem;
import com.smartYummy.service.ItemService;
import com.smartYummy.service.OrderService;
import com.smartYummy.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Controller
@RequestMapping("/shopping")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addItem(@RequestParam("item_id") long item_id,
                 @RequestParam("quantity") int quantity,
                 Authentication authentication) {
        OrderItem orderItem = getOrderItem(authentication, item_id);
        orderItem.setQuantity(quantity);
        shoppingCartService.addItem(orderItem);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    void updateItem(@RequestParam("item_id") long item_id,
                    @RequestParam("quantity") int quantity,
                    Authentication authentication) {
        OrderItem orderItem = getOrderItem(authentication, item_id);
        orderItem.setQuantity(quantity);
        shoppingCartService.updateItem(orderItem);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    void removeItem(@RequestParam("item_id") long item_id,
                    Authentication authentication) {
        OrderItem orderItem = getOrderItem(authentication, item_id);
        shoppingCartService.removeItem(orderItem);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<OrderItem> getItems() {
        return shoppingCartService.getOrderItems();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    void save(Authentication authentication) {
        Order order = new Order();
        // insert user bean
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        order.setUser(currentUser.getUser());
        System.out.println(currentUser.getUser());

        List<OrderItem> itemList = shoppingCartService.getOrderItems();
        order.setOrderItems(itemList);
        orderService.saveOrder(order);
    }

    private OrderItem getOrderItem(Authentication authentication, long item_id) {
        OrderItem orderItem = new OrderItem();
        // insert item bean
        orderItem.setItem(itemService.findByID(item_id));
        // insert user bean
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        orderItem.setUser(currentUser.getUser());
        System.out.println(currentUser.getUser());
        return orderItem;
    }
}
