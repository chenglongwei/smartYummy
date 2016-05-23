package com.smartYummy.controller;

import com.smartYummy.model.Item;
import com.smartYummy.model.Order;
import com.smartYummy.service.ItemService;
import com.smartYummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenglongwei on 5/22/16.
 */
@Controller
@RequestMapping("/admin/report")
public class AdminReportController {

    @Autowired
    OrderService orderService;
    @Autowired
    ItemService itemService;

    // order can be "create" or "start"
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String reportOrder(Model model,
                              @RequestParam("from") Date from,
                              @RequestParam("to") Date to,
                              @RequestParam("order") String order) {
        List<Order> orders;
        if ("start".equals(order)) {
            orders = orderService.findOrdersByCreateTimeOrderByStartTime(from, to);
        } else {
            orders = orderService.findOrdersByCreateTimeOrderByCreateTime(from, to);
        }

        model.addAttribute("orders", orders);
        return "admin/report/order";
    }

    // order can be "create" or "start"
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String reportOrder(Model model,
                              @RequestParam("id") long id) {
        model.addAttribute("order", orderService.findByID(id));
        return "admin/report/detail";
    }

    // order can be "create" or "start"
    @RequestMapping(value = "/order/all", method = RequestMethod.GET)
    public String reportOrderList(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/report/order";
    }

    @RequestMapping(value = "/popularity", method = RequestMethod.GET)
    public String reportItem(Model model,
                             @RequestParam("from") Date from,
                             @RequestParam("to") Date to,
                             @RequestParam("category") String category) {
        /**
         * item tag: 0 means inactive, 1 means active
         */
        List<Item> items = itemService.findByCategory(category);
        model.addAttribute("items", items);
        return "admin/report/order";
    }

//    private List<Item> filter(List<Item> items, Date from, Date to) {
//        List<Item> filteredItems = new ArrayList<Item>();
//        for (Item item : items) {
//
//        }
//    }
}
