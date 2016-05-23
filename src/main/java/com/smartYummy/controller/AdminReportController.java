package com.smartYummy.controller;

import com.smartYummy.model.Item;
import com.smartYummy.model.ItemCount;
import com.smartYummy.model.Order;
import com.smartYummy.model.OrderItem;
import com.smartYummy.service.ItemService;
import com.smartYummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-05-19
 *
 * Administrator's url to get report: order report and popularity report.
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
    public String reportItems(Model model,
                              @RequestParam("from") Date from,
                              @RequestParam("to") Date to,
                              @RequestParam("category") String category) {
        List<Item> items = itemService.findByCategory(category);
        List<Order> orders = orderService.findOrdersByCreateTimeOrderByCreateTime(from, to);
        model.addAttribute("item_count", sortItemByOrderCount(items, orders));
        return "admin/report/popularity";
    }

    @RequestMapping(value = "/popularity/all", method = RequestMethod.GET)
    public String reportItems(Model model) {
        List<Item> items = itemService.findByCategory("main");
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("item_count", sortItemByOrderCount(items, orders));
        return "admin/report/popularity";
    }

    /**
     *
     * @param items
     * @param orders
     * @return a sorted itemCount data structure based on item's order times.
     */
    private List<ItemCount> sortItemByOrderCount(List<Item> items, List<Order> orders) {
        List<ItemCount> itemCounts = new ArrayList<ItemCount>(items.size());
        for (Item item : items) {
            ItemCount itemCount = new ItemCount();
            itemCount.setItem(item);
            itemCount.setCount(getItemCount(orders, item));
            itemCounts.add(itemCount);
        }

        Collections.sort(itemCounts);
        return itemCounts;
    }

    private int getItemCount(List<Order> orders, Item item) {
        int count = 0;
        for (Order order : orders) {
            if (orderContainsItem(order, item)) {
                count++;
            }
        }
        return count;
    }

    private boolean orderContainsItem(Order order, Item item) {
        for (OrderItem orderItem : order.getOrderItems()) {
            if (item.getId() == orderItem.getItem().getId()) {
                return true;
            }
        }

        return false;
    }
}
