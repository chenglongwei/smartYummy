package com.smartYummy.controller;

import com.smartYummy.model.*;
import com.smartYummy.service.ItemService;
import com.smartYummy.service.OrderService;
import com.smartYummy.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    YummyResponse removeItem(@RequestParam("id") long id) {
        Order order = orderService.findByID(id);
        YummyResponse response = new YummyResponse();
        if (!order.getStatus().equals("not started")) {
            System.out.println("delete a started order");
            response.setStatus("fail");
            return response;
        }

        orderService.deleteByID(id);
        response.setStatus("success");
        return response;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    String getItems(Model model, Authentication authentication) {
        model.addAttribute("orders", orderService.getOrders(getUser(authentication).getId()));
        return "order/list";
    }

    private User getUser(Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        return currentUser.getUser();
    }
}
