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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenglongwei on 5/22/16.
 */
@Controller
@RequestMapping("/admin/item")
public class AdminItemController {
    /**
     * Auto wire a profile service, @see com.smartYummy.service.ItemService.
     * The service has transaction to deal with database read/write/delete/update.
     */
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@ModelAttribute Item item) {
        return "admin/item/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Item item, MultipartFile file, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            throw new YummyException("bind item has errors");
        }
        item.setTag(1);
        itemService.insertItem(item);
        long id = item.getId();
        try {
            multipartToFile(file, "/Users/StarRUC/git/smartYummy/images/" + id + ".jpg");
        } catch (Exception e) {
            e.printStackTrace();

        }
        redirect.addFlashAttribute("globalMessage", "Successfully created a new item");
        return "redirect:/admin/item/create";
    }

    public File multipartToFile(MultipartFile multipart, String name) throws IllegalStateException, IOException {
        File convFile = new File(name);
        multipart.transferTo(convFile);
        return convFile;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String adminListItem(Model model) {
        List<Item> items = itemService.listAllItems();
        model.addAttribute("items", items);
        return "admin/item/list";
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
