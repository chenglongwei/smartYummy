package com.smartYummy.controller;

import com.smartYummy.model.CurrentUser;
import com.smartYummy.model.Order;
import com.smartYummy.model.OrderItem;
import com.smartYummy.model.User;
import com.smartYummy.service.ItemService;
import com.smartYummy.service.OrderService;
import com.smartYummy.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NOT_MODIFIED)
    void removeItem(@RequestParam("id") long id) {
        Order order = orderService.findByID(id);
        if (!order.getStatus().equals("not started")) {
            System.out.println("delete a started order");
            return;
        }

        orderService.deleteByID(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<Order> getItems(Authentication authentication) {
        return orderService.getOrders(getUser(authentication).getId());
    }

    private User getUser(Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        return currentUser.getUser();
    }
}
