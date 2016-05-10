package com.smartYummy.controller;

/**
 * Created by chenglongwei on 4/21/16.
 */

import com.smartYummy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * A class to test interactions with the MySQL database using the UserService class.
 *
 * @author chenglongwei
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private StringRedisTemplate redisTemplate;

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

    @RequestMapping(value = "/sendcode", method = RequestMethod.POST)
    public void sendMail(@RequestParam("email") String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("SmartYummy: Registration Code");
        String randomCode = getRandomCode();
        mailMessage.setText("Registration Code is: " + randomCode);
        javaMailSender.send(mailMessage);

        // add redis the temp code
        redisTemplate.opsForSet().add(getVerifyEmailKey(email), randomCode);
        redisTemplate.expire(getVerifyEmailKey(email), 5, TimeUnit.HOURS);
    }

    private String getVerifyEmailKey(String email) {
        return "verify_" + email + "_code";
    }

    private String getRandomCode() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
