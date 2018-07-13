package com.yuan.demo.controller;

import com.yuan.demo.model.User;
import com.yuan.demo.sevice.InitDatabaseService;
import com.yuan.demo.sevice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger("HomeController");
    @Autowired
    private UserService userService;
    @Autowired
    private InitDatabaseService initDatabaseService;

    @RequestMapping("/")
    public String root(Model model){
        logger.warn("重定向到 /home");
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    @RequestMapping("/home")
    public String home(Model model){
        logger.info("从 security 容器中获取到的用户名: "+sessionUserName());
        User user = userService.getByUserName("admin");
        model.addAttribute("user",user);
        return "home";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "/admin";
    }

    @RequestMapping("/admin/reInitDB")
    public @ResponseBody String reInitDB(){
        try {
            initDatabaseService.reCreateTableTest();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"code\":500}";
        }
        return "{\"code\":200}";
    }
}
