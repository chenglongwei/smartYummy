package com.smartYummy.controller;

/**
 * Created by chenglongwei on 4/21/16.
 */

import com.smartYummy.model.Role;
import com.smartYummy.model.User;
import com.smartYummy.model.YummyResponse;
import com.smartYummy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    YummyResponse register(@RequestParam("email") String email,
                           @RequestParam("password1") String password1,
                           @RequestParam("password2") String password2,
                           @RequestParam("code") String code,
                           RedirectAttributes redirect) {

        YummyResponse response = new YummyResponse();

        // verify password1 is same with password2
        if (!password1.equals(password2)) {
//            redirect.addFlashAttribute("globalMessage", "Repeated password is not same!");
//            return "user/register";
            response.setError("Repeated password is not same!");
            response.setStatus("fail");
            return response;
        }
        // verify code
        String verifiedCode = redisTemplate.opsForValue().get(getVerifyEmailKey(email));
        if (!code.equals(verifiedCode)) {
//            redirect.addFlashAttribute("globalMessage", "Email code is not verified!");
//            return "user/register";
            response.setError("Email code is not verified!");
            response.setStatus("fail");
            return response;

        }

        // create user
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptPassword = encoder.encode(password1);
        User user = new User();
        user.setEmail(email);
        user.setPassword(encryptPassword);
        user.setRole(Role.CUSTOMER);

        // insert into table
        userService.insertUser(user);
//        redirect.addFlashAttribute("globalMessage", "Successfully created a new item");
//        return "redirect:login";
        response.setStatus("success");
        return response;

    }

    @RequestMapping(value = "/sendcode", method = RequestMethod.POST)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ResponseStatus(value = HttpStatus.OK)
    public void sendMail(@RequestParam("email") String email) {
        // produce a random code
        String randomCode = getRandomCode();

        // add redis the temp code
        redisTemplate.opsForValue().set(getVerifyEmailKey(email), randomCode);
        redisTemplate.expire(getVerifyEmailKey(email), 5, TimeUnit.HOURS);

        // send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("SmartYummy: Registration Code");
        mailMessage.setText("Registration Code is: " + randomCode + ", code expires in 5 minutes.");
        javaMailSender.send(mailMessage);
    }

    private String getVerifyEmailKey(String email) {
        return "verify_" + email + "_code";
    }

    private String getRandomCode() {
        return UUID.randomUUID().toString().substring(0, 4);
    }
}
