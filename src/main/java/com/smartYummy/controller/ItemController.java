package com.smartYummy.controller;

import com.smartYummy.model.Item;
import com.smartYummy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenglongwei
 * @version 1.0
 * @since 2016-03-27
 * Item Controller, support C.R.U.D. of menu Items.
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    /**
     * Auto wire a profile service, @see com.smartYummy.service.ItemService.
     * The service has transaction to deal with database read/write/delete/update.
     */
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listItem(Model model) {
        /**
         * item tag: 0 means inactive, 1 means active
         */
        List<Item> items = itemService.findByTag(1);
        model.addAttribute("items", items);
        return "item/list";
    }

    @RequestMapping(value = "/list/{category}", method = RequestMethod.GET)
    public String listItemByTag(@PathVariable("category") String category, Model model) {
        /**
         * item tag: 0 means inactive, 1 means active
         */
        List<Item> items = itemService.findByCategoryAndTag(category, 1);
        model.addAttribute("items", items);
        return "item/list";
    }
}
