package com.yuan.demo.controller;

import com.yuan.demo.model.User;
import com.yuan.demo.sevice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger("HomeController");
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String root(Model model){
        logger.warn("重定向到 /home");
        return "home";
    }

    @RequestMapping("/home")
    public String home(Model model){
        User user = userService.getByUserName("admin");
        model.addAttribute("user",user);
        return "home";
    }
}
