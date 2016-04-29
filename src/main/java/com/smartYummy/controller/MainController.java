package com.smartYummy.controller;

/**
 * Created by chenglongwei on 4/21/16.
 */

import com.smartYummy.Exception.YummyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        if (name.startsWith("er"))
            throw new YummyException("yummy error!");
        return "welcome";
    }

}
