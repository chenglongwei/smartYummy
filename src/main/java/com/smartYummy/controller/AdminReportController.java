package com.smartYummy.controller;

import com.smartYummy.model.Item;
import com.smartYummy.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by chenglongwei on 5/22/16.
 */
@Controller
@RequestMapping("/admin/report")
public class AdminReportController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listItem(Model model) {
        /**
         * item tag: 0 means inactive, 1 means active
         */
//        List<Item> items = itemService.findByTag(1);
//        model.addAttribute("items", items);
        return "item/list";
    }
}
