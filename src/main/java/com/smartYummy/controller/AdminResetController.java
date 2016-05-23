package com.smartYummy.controller;

import com.smartYummy.model.CurrentUser;
import com.smartYummy.model.Order;
import com.smartYummy.model.User;
import com.smartYummy.model.YummyResponse;
import com.smartYummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Controller
@RequestMapping("/admin")
public class AdminResetController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    YummyResponse removeItem(@RequestParam("id") long id) {
        Order order = orderService.findByID(id);
        YummyResponse response = new YummyResponse();
        if (!order.getStatus().equals(Order.NOT_STARTED)) {
            System.out.println("delete a started order");
            response.setStatus("fail");
            return response;
        }

        orderService.deleteByID(id);
        response.setStatus("success");
        return response;
    }
}
