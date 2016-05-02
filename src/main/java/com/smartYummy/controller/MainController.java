package com.smartYummy.controller;

/**
 * Created by chenglongwei on 4/21/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "welcome";
    }
}
