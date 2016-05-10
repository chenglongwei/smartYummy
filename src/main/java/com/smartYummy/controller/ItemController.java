package com.smartYummy.controller;

import com.smartYummy.exception.YummyException;
import com.smartYummy.model.Item;
import com.smartYummy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute Item item) {
        return "item/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Item item, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            throw new YummyException("bind item has errors");
        }
        itemService.insertItem(item);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new item");
        return "redirect:/item/create";
    }

    @RequestMapping(value = "/adminlist", method = RequestMethod.GET)
    public String adminListItem(Model model) {
        List<Item> items = itemService.listAllItems();
        model.addAttribute("items", items);
        return "item/adminlist";
    }

    @RequestMapping(value = "/update/tag", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void changeTag(@RequestParam("id") long id) {
        Item item = itemService.findByID(id);
        /**
         * item tag: 0 means inactive, 1 means active
         */
        itemService.setTag(item, item.getTag() == 0 ? 1 : 0);
    }
}
