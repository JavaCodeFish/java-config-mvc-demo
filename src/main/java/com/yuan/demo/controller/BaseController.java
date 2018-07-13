package com.yuan.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {

    protected String sessionUserName(){
        String username = null;
        try {
            UserDetails sessionUser = (UserDetails)SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getPrincipal();
            username = sessionUser.getUsername();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }
}
