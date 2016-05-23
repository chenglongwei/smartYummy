package com.smartYummy.controller;

import com.smartYummy.model.YummyResponse;
import com.smartYummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-05-19
 *
 * Administrator's url to reset all orders.
 */
@Controller
@RequestMapping("/admin")
public class AdminResetController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    YummyResponse resetOrders() {
        YummyResponse response = new YummyResponse();
        orderService.resetOrders();
        response.setStatus("success");
        return response;
    }
}
