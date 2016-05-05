package com.smartYummy.controller;

/**
 * Created by chenglongwei on 4/21/16.
 */

import com.smartYummy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * A class to test interactions with the MySQL database using the UserService class.
 * @author chenglongwei
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("user/login", "error", error);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        System.out.println("Income request for Register");
        return "user/register";
    }
//    public ModelAndView register(@RequestParam Optional<String> error) {
//        System.out.println("Income request for Register");
//        return new ModelAndView("user/register", "error", error);
//    }
}
